package org.example.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {

    @Value("Cyber Center PTIT")
    private String branchName;

    @Value("08:00")
    private String openingHour;

    public String getBranchName() {
        return branchName;
    }

    public String getOpeningHour() {
        return openingHour;
    }
}