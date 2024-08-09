package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.dto.EmergencyHospitalCapacityDto;
import com.emergency_link.emergency_link.dto.EmergencyHospitalInfoDto;
import com.emergency_link.emergency_link.dto.HospitalPosDto;
import com.emergency_link.emergency_link.entity.EmergencyHospitalCapacity;
import com.emergency_link.emergency_link.entity.EmergencyHospitalInfo;
import com.emergency_link.emergency_link.entity.HospitalPos;
import com.emergency_link.emergency_link.repository.EmergencyHospitalCapacityRepository;
import com.emergency_link.emergency_link.repository.EmergencyHospitalInfoRepository;
import com.emergency_link.emergency_link.repository.HospitalPosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Transactional
public class GetPublicData {

    private static final Logger logger = LoggerFactory.getLogger(GetPublicData.class);
    private final EmergencyHospitalCapacityRepository emergencyHospitalCapacityRepository;
    private final EmergencyHospitalInfoRepository emergencyHospitalInfoRepository;
    private final HospitalPosRepository emergencyHospitalPosRepository;

    @Value("${serviceKey}")
    String serviceKey;

    @GetMapping("/test")
    public String getEmrrmRltmUsefulSckbdInfoInqire() {
        int totalDataCount = 102574; // 총 데이터 개수
        int pageSize = 1000; // 한 번에 가져올 데이터 개수
        int totalPages = (int) Math.ceil((double) totalDataCount / pageSize);

        for (int page = 1; page <= totalPages; page++) {
            try {
                logger.info("Preparing API request for page {}", page);
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytBassInfoInqire");
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(page), "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageSize), "UTF-8"));

                System.out.println(urlBuilder.toString());

                URI uri = new URI(urlBuilder.toString());
                logger.info("Request URI: {}", uri);

                // CloseableHttpClient 사용
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    HttpGet httpGet = new HttpGet(uri);
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        if (response.getStatusLine().getStatusCode() == 200) {
                            // 응답을 UTF-8로 변환
                            String xmlResponse = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                            // logger.info("API response received: {}", xmlResponse);

                            // Parse XML response
                            parseXmlResponse(xmlResponse);

                        } else {
                            logger.error("Error in API response: {}", response.getStatusLine().getStatusCode());
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("Error making API request for page {}", page, e);
            }
        }

        return "Data request completed";
    }


    @GetMapping("/test2")
    public String getDutyEmcls() {
        int totalDataCount = 520; // 총 데이터 개수
        int pageSize = 520; // 한 번에 가져올 데이터 개수
        int totalPages = (int) Math.ceil((double) totalDataCount / pageSize);

        for (int page = 1; page <= totalPages; page++) {
            try {
                logger.info("Preparing API request for page {}", page);
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytListInfoInqire");
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(page), "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageSize), "UTF-8"));

                // System.out.println(urlBuilder.toString());

                URI uri = new URI(urlBuilder.toString());
                // logger.info("Request URI: {}", uri);

                // CloseableHttpClient 사용
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    HttpGet httpGet = new HttpGet(uri);
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        if (response.getStatusLine().getStatusCode() == 200) {
                            // 응답을 UTF-8로 변환
                            String xmlResponse = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                            logger.info("API response received: {}", xmlResponse);

                            // Parse XML response
                            parseXmlResponse2(xmlResponse);

                        } else {
                            logger.error("Error in API response: {}", response.getStatusLine().getStatusCode());
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("Error making API request for page {}", page, e);
            }
        }

        return "Data request completed";
    }

    private void parseXmlResponse(String xmlResponse) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlResponse)));

            NodeList nodeList = doc.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element == null) {
                    logger.warn("Element at index {} is null", i);
                    continue;
                }

                if(parseBoolean(getTagValue("dutyEryn",element)) != true) continue;

                // HospitalInfo 저장
                EmergencyHospitalInfoDto hospitalInfoDto = new EmergencyHospitalInfoDto();

                hospitalInfoDto.setHpid(getTagValue("hpid", element));
                hospitalInfoDto.setDutyName(getTagValue("dutyName", element));
                hospitalInfoDto.setDutyEmcls(null);
                hospitalInfoDto.setDutyEmclsName(null);
                hospitalInfoDto.setPostCdn1(parseInteger(getTagValue("postCdn1",element)));
                hospitalInfoDto.setPostCdn2(parseInteger(getTagValue("postCdn2",element)));
                hospitalInfoDto.setDutyAddr(getTagValue("dutyAddr",element));
                hospitalInfoDto.setDutyTel1(getTagValue("dutyTel1",element));
                hospitalInfoDto.setDutyTel2(getTagValue("dutyTel3",element));
                hospitalInfoDto.setDutyHayn(parseBoolean(getTagValue("dutyHayn",element)));
                hospitalInfoDto.setDutyEryn(parseBoolean(getTagValue("dutyEryn",element)));

                hospitalInfoDto.setMKioskTy1(parseBoolean(getTagValue("MKioskTy1",element)));
                hospitalInfoDto.setMKioskTy2(parseBoolean(getTagValue("MKioskTy2",element)));
                hospitalInfoDto.setMKioskTy3(parseBoolean(getTagValue("MKioskTy3",element)));
                hospitalInfoDto.setMKioskTy4(parseBoolean(getTagValue("MKioskTy4",element)));
                hospitalInfoDto.setMKioskTy5(parseBoolean(getTagValue("MKioskTy5",element)));
                hospitalInfoDto.setMKioskTy6(parseBoolean(getTagValue("MKioskTy6",element)));
                hospitalInfoDto.setMKioskTy7(parseBoolean(getTagValue("MKioskTy7",element)));
                hospitalInfoDto.setMKioskTy8(parseBoolean(getTagValue("MKioskTy8",element)));
                hospitalInfoDto.setMKioskTy9(parseBoolean(getTagValue("MKioskTy9",element)));
                hospitalInfoDto.setMKioskTy10(parseBoolean(getTagValue("MKioskTy10",element)));
                hospitalInfoDto.setMKioskTy11(parseBoolean(getTagValue("MKioskTy11",element)));

                hospitalInfoDto.setTraumaCenter(false);
                hospitalInfoDto.setLoginUser(0);

                // dto -> object
                EmergencyHospitalInfo emergencyHospitalInfo = new EmergencyHospitalInfo();
                emergencyHospitalInfo.setDtoToObject(hospitalInfoDto);

                // HospitalCapacity 저장
                EmergencyHospitalCapacityDto hospitalCapacityDto = new EmergencyHospitalCapacityDto();

                hospitalCapacityDto.setHvec(parseInteger(getTagValue("hvec", element)));
                hospitalCapacityDto.setHperyn(parseInteger(getTagValue("hperyn", element)));

                hospitalCapacityDto.setHvoc(parseInteger(getTagValue("hvoc", element)));
                hospitalCapacityDto.setHpopyn(parseInteger(getTagValue("hpopyn", element)));

                hospitalCapacityDto.setHvcc(parseInteger(getTagValue("hvcc", element)));
                hospitalCapacityDto.setHpcuyn(parseInteger(getTagValue("hpcuyn", element)));

                hospitalCapacityDto.setHvncc(parseInteger(getTagValue("hvncc", element)));
                hospitalCapacityDto.setHpnicuyn(parseInteger(getTagValue("hpnicuyn", element)));

                hospitalCapacityDto.setHvccc(parseInteger(getTagValue("hvccc", element)));
                hospitalCapacityDto.setHpccuyn(parseInteger(getTagValue("hpccuyn", element)));

                hospitalCapacityDto.setHvicc(parseInteger(getTagValue("hvicc", element)));
                hospitalCapacityDto.setHpicuyn(parseInteger(getTagValue("hpicuyn", element)));

                hospitalCapacityDto.setHvgc(parseInteger(getTagValue("hvgc", element)));
                hospitalCapacityDto.setHpgryn(parseInteger(getTagValue("hpgryn", element)));

                hospitalCapacityDto.setDutyHano(parseInteger(getTagValue("dutyHano", element)));
                hospitalCapacityDto.setHpbdn(parseInteger(getTagValue("hpbdn", element)));

                EmergencyHospitalCapacity emergencyHospitalCapacity = new EmergencyHospitalCapacity();
                emergencyHospitalCapacity.setDtoToObject(hospitalCapacityDto);

                // HospitalPos 저장
                HospitalPosDto hospitalPosDto = new HospitalPosDto();

                hospitalPosDto.setLatitude(parseDouble(getTagValue("wgs84Lat",element)));
                hospitalPosDto.setLongitude(parseDouble(getTagValue("wgs84Lon",element)));

                HospitalPos hospitalPos = new HospitalPos();
                hospitalPos.setDtoToObject(hospitalPosDto);

                // HospitalInfo HospitalCapacity 양방향 설정
                emergencyHospitalCapacity.setEmergencyHospitalInfo(emergencyHospitalInfo);

                // HospitalInfo HospitalPos 양방향 설정
                hospitalPos.setEmergencyHospitalInfo(emergencyHospitalInfo);

                // DB 저장
                emergencyHospitalInfoRepository.save(emergencyHospitalInfo);
                emergencyHospitalCapacityRepository.save(emergencyHospitalCapacity);
                emergencyHospitalPosRepository.save(hospitalPos);

            }
        } catch (Exception e) {
            logger.error("Error parsing XML response", e);
        }
    }

    private void parseXmlResponse2(String xmlResponse){
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlResponse)));

            NodeList nodeList = doc.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element == null) {
                    logger.warn("Element at index {} is null", i);
                    continue;
                }
                Optional<EmergencyHospitalInfo> a = emergencyHospitalInfoRepository.findByHpid(getTagValue("hpid",element));
                if(!a.isPresent()){
                    System.out.println(getTagValue("dutyName",element)+"가 존재하지 않습니다");
                }else {
//                    System.out.println(a.get().getDutyName());
                    EmergencyHospitalInfo hospitalInfo = a.get();

                    // dto -> object
                    EmergencyHospitalInfoDto hospitalInfoDto = new EmergencyHospitalInfoDto(hospitalInfo);

                    hospitalInfoDto.setDutyEmcls(getTagValue("dutyEmcls",element));
                    hospitalInfoDto.setDutyEmclsName(getTagValue("dutyEmclsName",element));

                    EmergencyHospitalInfo emergencyHospitalInfo = new EmergencyHospitalInfo();
                    emergencyHospitalInfo.setDtoToObject(hospitalInfoDto); // update

                }


            }
        } catch (Exception e) {
            logger.error("Error parsing XML response", e);
        }
    }

    private String elementToString(Element element) {
        try {
            StringWriter writer = new StringWriter();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(element), new StreamResult(writer));
            return writer.toString();
        } catch (Exception e) {
            logger.error("Error converting element to string", e);
            return "";
        }
    }

    private String getTagValue(String tag, Element element) {
        try {
            NodeList nodeList = element.getElementsByTagName(tag);
            if (nodeList.getLength() > 0) {
                NodeList subNodeList = nodeList.item(0).getChildNodes();
                if (subNodeList.getLength() > 0) {
                    Node node = subNodeList.item(0);
                    return node.getNodeValue().trim();
                }
            }
        } catch (Exception e) {
            logger.error("Error getting tag value for tag: {}", tag, e);
        }
        return null;
    }

    private Integer parseInteger(String value) {
        try {
            return (value != null) ? Integer.parseInt(value) : null;
        } catch (NumberFormatException e) {
            logger.error("Error parsing integer value: {}", value, e);
            return null;
        }
    }

    private Boolean parseBoolean(String value){
        if(value == null) return false;
        if(value.equalsIgnoreCase("1") || value.equalsIgnoreCase("Y")) return true;
        return false;
    }

    private Double parseDouble(String value){
        if(value == null) return null;
        return Double.parseDouble(value);
    }
}
