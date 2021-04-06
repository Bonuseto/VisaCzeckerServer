package main.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

//Getting status of application by using Jsoup
public class StatusCheck {

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
        String inprocessStatus = String.valueOf(document.getElementsMatchingOwnText("In Process"));
        String rejectedStatus = String.valueOf(document.getElementsMatchingOwnText("Decided – REJECTED"));
        String notFoundStatus = String.valueOf(document.getElementsMatchingOwnText("Not found"));

        String result = "Something went wrong";

        if (!approvedStatus.isEmpty()) {
            result = "Decided - Approved";
        }
        if (!inprocessStatus.isEmpty()) {
            result = "In Process";
        }
        if (!rejectedStatus.isEmpty()) {
            result = "Decided - Rejected";
        }
        if (!notFoundStatus.isEmpty()) {
            result = "Not found";
        }
        return result;
    }
}
