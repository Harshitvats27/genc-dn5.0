// Factory Method Pattern Exercise
// Exercise 2: Implementing the Factory Method Pattern

// Document interface
interface Document {
    String getType();
    void open();
    void save();
    void close();
}

// Concrete Document Classes
class WordDocument implements Document {
    @Override
    public String getType() {
        return "Word Document";
    }

    @Override
    public void open() {
        System.out.println("Opening Microsoft Word document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document...");
    }

    @Override
    public void close() {
        System.out.println("Closing Word document...");
    }
}

class PdfDocument implements Document {
    @Override
    public String getType() {
        return "PDF Document";
    }

    @Override
    public void open() {
        System.out.println("Opening Adobe PDF document...");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document...");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF document...");
    }
}

class ExcelDocument implements Document {
    @Override
    public String getType() {
        return "Excel Document";
    }

    @Override
    public void open() {
        System.out.println("Opening Microsoft Excel spreadsheet...");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel spreadsheet...");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel spreadsheet...");
    }
}

// Abstract DocumentFactory class
abstract class DocumentFactory {
    // Factory method to create documents
    public abstract Document createDocument();

    // Template method that uses the factory method
    public void processDocument() {
        Document document = createDocument();
        System.out.println("Created: " + document.getType());
        document.open();
        document.save();
        document.close();
    }
}

// Concrete Factory Classes
class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// Test class to demonstrate Factory Method Pattern
public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===\n");

        // Create a Word document using the factory
        System.out.println("--- Creating Word Document ---");
        DocumentFactory wordFactory = new WordDocumentFactory();
        wordFactory.processDocument();

        System.out.println();

        // Create a PDF document using the factory
        System.out.println("--- Creating PDF Document ---");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        pdfFactory.processDocument();

        System.out.println();

        // Create an Excel document using the factory
        System.out.println("--- Creating Excel Document ---");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        excelFactory.processDocument();

        System.out.println();

        // Direct creation without template method
        System.out.println("--- Direct Document Creation ---");
        Document wordDoc = new WordDocumentFactory().createDocument();
        Document pdfDoc = new PdfDocumentFactory().createDocument();
        Document excelDoc = new ExcelDocumentFactory().createDocument();

        System.out.println("Word Document Type: " + wordDoc.getType());
        System.out.println("PDF Document Type: " + pdfDoc.getType());
        System.out.println("Excel Document Type: " + excelDoc.getType());
    }
}
