package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.entity.EmergencyHospitalsCapacity;
import com.emergency_link.emergency_link.entity.EmergencyHospitalsInfo;
import com.emergency_link.emergency_link.entity.HospitalPos;
import com.emergency_link.emergency_link.repository.EmergencyHospitalsCapacityRepository;
import com.emergency_link.emergency_link.repository.EmergencyHospitalsInfoRepository;
import com.emergency_link.emergency_link.repository.EmergencyHospitalsPosRepository;
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
public class GetPublicData {

    private static final Logger logger = LoggerFactory.getLogger(GetPublicData.class);

    @Autowired
    private EmergencyHospitalsCapacityRepository emergencyHospitalsCapacityRepository;

    @Autowired
    private EmergencyHospitalsInfoRepository emergencyHospitalsInfoRepository;

    @Autowired
    private EmergencyHospitalsPosRepository emergencyHospitalsPosRepository;

    @Value("${serviceKey}")
    String serviceKey;

    @GetMapping("/test")
    public String getEmrrmRltmUsefulSckbdInfoInqire() {
        System.out.println("Hello");
        int totalDataCount = 102574; // 총 데이터 개수
        int pageSize = 1000; // 한 번에 가져올 데이터 개수
        int totalPages = (int) Math.ceil((double) totalDataCount / pageSize);

        for (int page = 73; page <= totalPages; page++) {
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
                EmergencyHospitalsInfo hospitalInfo = new EmergencyHospitalsInfo();
                hospitalInfo.setHpid(getTagValue("hpid", element));
                hospitalInfo.setDutyName(getTagValue("dutyName", element));
                hospitalInfo.setDutyEmcls(null);
                hospitalInfo.setDutyEmclsName(null);
                hospitalInfo.setPostCdn1(parseInteger(getTagValue("postCdn1",element)));
                hospitalInfo.setPostCdn2(parseInteger(getTagValue("postCdn2",element)));
                hospitalInfo.setDutyAddr(getTagValue("dutyAddr",element));
                hospitalInfo.setDutyTel1(getTagValue("dutyTel1",element));
                hospitalInfo.setDutyTel2(getTagValue("dutyTel3",element));
                hospitalInfo.setDutyHayn(parseBoolean(getTagValue("dutyHayn",element)));
                hospitalInfo.setDutyEryn(parseBoolean(getTagValue("dutyEryn",element)));

                hospitalInfo.setMKioskTy1(parseBoolean(getTagValue("MKioskTy1",element)));
                hospitalInfo.setMKioskTy2(parseBoolean(getTagValue("MKioskTy2",element)));
                hospitalInfo.setMKioskTy3(parseBoolean(getTagValue("MKioskTy3",element)));
                hospitalInfo.setMKioskTy4(parseBoolean(getTagValue("MKioskTy4",element)));
                hospitalInfo.setMKioskTy5(parseBoolean(getTagValue("MKioskTy5",element)));
                hospitalInfo.setMKioskTy6(parseBoolean(getTagValue("MKioskTy6",element)));
                hospitalInfo.setMKioskTy7(parseBoolean(getTagValue("MKioskTy7",element)));
                hospitalInfo.setMKioskTy8(parseBoolean(getTagValue("MKioskTy8",element)));
                hospitalInfo.setMKioskTy9(parseBoolean(getTagValue("MKioskTy9",element)));
                hospitalInfo.setMKioskTy10(parseBoolean(getTagValue("MKioskTy10",element)));
                hospitalInfo.setMKioskTy11(parseBoolean(getTagValue("MKioskTy11",element)));

                hospitalInfo.setTraumaCenter(false);
                hospitalInfo.setLoginUser(0);

                // HospitalCapacity 저장
                EmergencyHospitalsCapacity hospitalCapacity = new EmergencyHospitalsCapacity();

                hospitalCapacity.setHvec(parseInteger(getTagValue("hvec", element)));
                hospitalCapacity.setHperyn(parseInteger(getTagValue("hperyn", element)));

                hospitalCapacity.setHvoc(parseInteger(getTagValue("hvoc", element)));
                hospitalCapacity.setHpopyn(parseInteger(getTagValue("hpopyn", element)));

                hospitalCapacity.setHvcc(parseInteger(getTagValue("hvcc", element)));
                hospitalCapacity.setHpcuyn(parseInteger(getTagValue("hpcuyn", element)));

                hospitalCapacity.setHvncc(parseInteger(getTagValue("hvncc", element)));
                hospitalCapacity.setHpnicuyn(parseInteger(getTagValue("hpnicuyn", element)));

                hospitalCapacity.setHvccc(parseInteger(getTagValue("hvccc", element)));
                hospitalCapacity.setHpccuyn(parseInteger(getTagValue("hpccuyn", element)));

                hospitalCapacity.setHvicc(parseInteger(getTagValue("hvicc", element)));
                hospitalCapacity.setHpicuyn(parseInteger(getTagValue("hpicuyn", element)));

                hospitalCapacity.setHvgc(parseInteger(getTagValue("hvgc", element)));
                hospitalCapacity.setHpgryn(parseInteger(getTagValue("hpgryn", element)));

                hospitalCapacity.setDutyHano(parseInteger(getTagValue("dutyHano", element)));
                hospitalCapacity.setHpbdn(parseInteger(getTagValue("hpbdn", element)));


                // HospitalPos 저장
                HospitalPos hospitalPos = new HospitalPos();

                hospitalPos.setLatitude(parseDouble(getTagValue("wgs84Lat",element)));
                hospitalPos.setLongitude(parseDouble(getTagValue("wgs84Lon",element)));

                // HospitalInfo HospitalCapacity 양방향 설정
                hospitalCapacity.setEmergencyHospitalsInfo(hospitalInfo);
                hospitalInfo.setEmergencyHospitalsCapacity(hospitalCapacity);

                // HospitalInfo HospitalPos 양방향 설정
                hospitalPos.setEmergencyHospitalsInfo(hospitalInfo);
                hospitalInfo.setHospitalPos(hospitalPos);

                // DB 저장
                emergencyHospitalsInfoRepository.save(hospitalInfo);
                emergencyHospitalsCapacityRepository.save(hospitalCapacity);
                emergencyHospitalsPosRepository.save(hospitalPos);

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
                Optional<EmergencyHospitalsInfo> a = emergencyHospitalsInfoRepository.findByHpid(getTagValue("hpid",element));
                if(!a.isPresent()){
                    System.out.println(getTagValue("dutyName",element)+"가 존재하지 않습니다");
                }else {
//                    System.out.println(a.get().getDutyName());
                    EmergencyHospitalsInfo hospitalInfo = a.get();
                    hospitalInfo.setDutyEmcls(getTagValue("dutyEmcls",element));
                    hospitalInfo.setDutyEmclsName(getTagValue("dutyEmclsName",element));
                    emergencyHospitalsInfoRepository.save(hospitalInfo);
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
