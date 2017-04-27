import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

/**
 * Created by 4oc3p on 28.04.2017. xmlunit
 */
public class XmlUnitTest {

    public static void main(String[] args)
            throws FileNotFoundException, ParserConfigurationException {
        File f1 = new File(XmlUnitTest.class.getResource("Sample.xml").getPath());
        File f2 = new File(XmlUnitTest.class.getResource("Sample2.xml").getPath());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);

        Diff myDiff = DiffBuilder.compare(Input.fromFile(f1))
                .withTest(Input.fromFile(f2))
                .withDocumentBuilderFactory(dbf)
                .checkForSimilar()
                .ignoreWhitespace()
                .build();

        Iterable<Difference> differences = myDiff.getDifferences();
        differences.forEach(System.out::println);

    }

}
