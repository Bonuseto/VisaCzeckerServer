import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class StatusCheck {

    static String result;

    public static String check(String applicationNumImport, String xxFieldImport, String applicationTypeImport, String yearImport) {

        Document document = null;
        try {
            document = Jsoup.connect("https://frs.gov.cz/en/ioff/application-status")
                    .data("ioff_application_number", applicationNumImport)
                    .data("ioff_application_number_fake", xxFieldImport)
                    .data("ioff_application_code", applicationTypeImport)
                    .data("ioff_application_year", yearImport)
                    .data("form_id", "ioff_application_status_form")
                    .data("honeypot_time", "1617192329|wrKL35XoiHwzqAlduShVGVGrXQjqg7-0i9sEfXbTyv8")
                    //.cookies(loginForm.cookies())
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String approvedStatus = String.valueOf(document.getElementsMatchingOwnText("Decided – APPROVED"));
        String inprocessStatus = String.valueOf(document.getElementsMatchingOwnText("Process"));
        String rejectedStatus = String.valueOf(document.getElementsMatchingOwnText("Decided – REJECTED"));

        if (!empty(approvedStatus)) {
            result = "Decided - Approved";
        } else if (!empty(inprocessStatus)) {
            result = "In Process";
        } else if (!empty(rejectedStatus)) {
            result = "Decided - Rejected";
        } else {
            result = "Error";
        }

        return result;
    }

    public static boolean empty(final String s) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }
}
