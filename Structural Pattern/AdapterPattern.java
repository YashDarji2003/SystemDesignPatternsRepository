

public class AdapterPattern {
    public static void main(String[] args) {

        XmlDataProvider dataProvider = new XmlDataProvider();
        IReports reports = new XmlDataProviderAdapter(dataProvider);
        Client c = new Client();
        String rawData = "Alice:32";
        c.getReport(reports, rawData);
    }
}

interface IReports {
    String getJsonData(String data1);
}

class XmlDataProviderAdapter implements IReports {

    XmlDataProvider xml;

    XmlDataProviderAdapter(XmlDataProvider xml) {
        this.xml = xml;
    }

    @Override
    public String getJsonData(String data1) {
        String data = xml.getXmlData(data1);
        Integer startName = data.indexOf("<name>") + 6;
        Integer endName = data.indexOf("</name>");
        String name = data.substring(startName, endName);

        Integer startid = data.indexOf("<id>") + 4;
        Integer endid = data.indexOf("</id>");
        String id = data.substring(startid, endid);

        return "{\"name\":\"" + name + "\" ,\"id\":" + id + "}";
    }
}

class XmlDataProvider {
    String getXmlData(String data) {
        Integer sep = data.indexOf(":");
        String name = data.substring(0, sep);
        String id = data.substring(sep + 1);
        return ("<user>" +
                "<name>" + name + "</name>" +
                "<id>" + id + "</id>" +
                "</user>");
    }
}

class Client {
    void getReport(IReports r, String rawData) {
        System.out.println("Processed Json" + r.getJsonData(rawData));
    }
}
