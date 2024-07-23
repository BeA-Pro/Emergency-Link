package com.emergency_link.emergency_link.controller;

import com.emergency_link.emergency_link.model.HospitalCapacity;
import com.emergency_link.emergency_link.model.HospitalInfo;
import com.emergency_link.emergency_link.model.HospitalPos;
import com.emergency_link.emergency_link.repository.HospitalCapacityRepository;
import com.emergency_link.emergency_link.repository.HospitalInfoRepository;
import com.emergency_link.emergency_link.repository.HospitalPosRepository;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class GetPublicData {

    private static final Logger logger = LoggerFactory.getLogger(GetPublicData.class);

    @Autowired
    private HospitalCapacityRepository hospitalCapacityRepository;

    @Autowired
    private HospitalInfoRepository hospitalInfoRepository;

    @Autowired
    private HospitalPosRepository hospitalPosRepository;

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
                HospitalInfo hospitalInfo = new HospitalInfo();
                hospitalInfo.setHpid(getTagValue("hpid", element));
                hospitalInfo.setDuty_name(getTagValue("dutyName", element));
                hospitalInfo.setPost_cdn1(parseInteger(getTagValue("postCdn1",element)));
                hospitalInfo.setPost_cdn2(parseInteger(getTagValue("postCdn2",element)));
                hospitalInfo.setDuty_addr(getTagValue("dutyAddr",element));
                hospitalInfo.setMain_phone(getTagValue("dutyTel1",element));
                hospitalInfo.setEr_phone(getTagValue("dutyTel3",element));
                hospitalInfo.setInpa_avail(parseBoolean(getTagValue("dutyHayn",element)));
                hospitalInfo.setEr_avail(parseBoolean(getTagValue("dutyEryn",element)));

                // HospitalCapacity 저장
                HospitalCapacity hospitalCapacity = new HospitalCapacity();

                hospitalCapacity.setA_er_rooms(parseInteger(getTagValue("hvec", element)));
                hospitalCapacity.setS_er_rooms(parseInteger(getTagValue("hperyn", element)));

                hospitalCapacity.setA_sur_rooms(parseInteger(getTagValue("hvoc", element)));
                hospitalCapacity.setS_sur_rooms(parseInteger(getTagValue("hpopyn", element)));

                hospitalCapacity.setA_neu_icu(parseInteger(getTagValue("hvcc", element)));
                hospitalCapacity.setS_neu_icu(parseInteger(getTagValue("hpcuyn", element)));

                hospitalCapacity.setA_neo_icu(parseInteger(getTagValue("hvncc", element)));
                hospitalCapacity.setS_neo_icu(parseInteger(getTagValue("hpnicuyn", element)));

                hospitalCapacity.setA_tho_icu(parseInteger(getTagValue("hvccc", element)));
                hospitalCapacity.setS_tho_icu(parseInteger(getTagValue("hpccuyn", element)));

                hospitalCapacity.setA_gen_icu(parseInteger(getTagValue("hvicc", element)));
                hospitalCapacity.setS_gen_icu(parseInteger(getTagValue("hpicuyn", element)));

                hospitalCapacity.setA_inpatient(parseInteger(getTagValue("hvgc", element)));
                hospitalCapacity.setS_inpatient(parseInteger(getTagValue("hpgryn", element)));

                hospitalCapacity.setA_beds(parseInteger(getTagValue("dutyHano", element)));
                hospitalCapacity.setS_beds(parseInteger(getTagValue("hpbdn", element)));


                // HospitalPos 저장
                HospitalPos hospitalPos = new HospitalPos();

                hospitalPos.setLatitude(parseDouble(getTagValue("wgs84Lat",element)));
                hospitalPos.setLongitude(parseDouble(getTagValue("wgs84Lon",element)));

                // HospitalInfo HospitalCapacity 양방향 설정
                hospitalCapacity.setHospitalInfo(hospitalInfo);
                hospitalInfo.setHospitalCapacity(hospitalCapacity);

                // HospitalInfo HospitalPos 양방향 설정
                hospitalPos.setHospitalInfo(hospitalInfo);
                hospitalInfo.setHospitalPos(hospitalPos);

                // DB 저장
                hospitalInfoRepository.save(hospitalInfo);
                hospitalCapacityRepository.save(hospitalCapacity);
                hospitalPosRepository.save(hospitalPos);

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
        if(value.equalsIgnoreCase("1")) return true;
        return false;
    }

    private Double parseDouble(String value){
        if(value == null) return null;
        return Double.parseDouble(value);
    }
}
