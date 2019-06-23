# editorjs-java
Java backend for https://editorjs.io/

## Usage

```java
DocumentParser docParser = new DocumentParser();
docParser.addParser("paragraph", new Paragraph());
docParser.addParser("header", new Header());

String jsonIn = request.getParameter("json"); // unsafe JSON
Document doc = docParser.parseJSON(new JSONObject(jsonIn));
    
String jsonOut = doc.toJSONObject().toString(); // safe JSON
String htmlOut = doc.toHTML(); // safe HTML
```
