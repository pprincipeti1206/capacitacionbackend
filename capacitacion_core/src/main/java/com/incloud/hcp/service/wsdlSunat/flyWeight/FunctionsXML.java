package com.incloud.hcp.service.wsdlSunat.flyWeight;

import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FunctionsXML {

    public static String getTagValueByAttributeHTML(NodeList nodeList, String NameLabel, String NameAttribute, String ValueAttribute) {
        Node node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            Node nodeObject = eElement.getElementsByTagName(NameLabel).item(0);
            if (nodeObject.getAttributes().getNamedItem(NameAttribute) != null) {
                Node nodeAttribute = nodeObject.getAttributes().getNamedItem(NameAttribute);
                if (nodeAttribute.toString().contains(ValueAttribute)) {
                    String result = nodeObject.getTextContent().trim();
                    return result;
                }
            }
        }
        return "Not found a result";
    }

    public static String getTagValueHTML(NodeList nodeList, String NameLabel) {
        Node node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            Node nodeObject = eElement.getElementsByTagName(NameLabel).item(0);
            String result = nodeObject.getTextContent().trim();
            return result;
        }
        return "Not found a result";
    }


    public static List<String> getTagValueIntoTagHTML(NodeList nodeList, String firstNameLabel, String conditional, String... someNameLabel) {
        List<String> lists = new ArrayList<>();
        Node node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            Integer nodeLength = eElement.getElementsByTagName(firstNameLabel).getLength();
            System.out.println(nodeLength);
            for (int i = 0; i < nodeLength; i++) {
                Node nodeObject = eElement.getElementsByTagName(firstNameLabel).item(i);
                String textContent = nodeObject.getTextContent().trim();
                if (textContent.equals(conditional)) {
                    int finalI = i;
                    Optional.ofNullable(someNameLabel)
                            .filter(strings -> strings.length > 0)
                            .ifPresent(strings -> {
                                for (int x = 0; x < strings.length; x++) {
                                    if (x == 0) {
                                        Node nodeTaxableAmount = eElement.getElementsByTagName(someNameLabel[x]).item(0);
                                        String textSubContent = nodeTaxableAmount.getTextContent().trim();
                                        lists.add(textSubContent);
                                    } else {
                                        Node nodeTaxAmount = eElement.getElementsByTagName(someNameLabel[x]).item(finalI);
                                        String textTaxAmount = nodeTaxAmount.getTextContent().trim();
                                        lists.add(textTaxAmount);
                                    }

                                }
                            });
                    return lists;
                }
            }
        }
        lists.add("Not found a result");
        return lists;
    }

    public static File convert(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
