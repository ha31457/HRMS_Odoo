package com.hss.hrms.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEmployeeDTO implements Serializable {

    private PersonalDetails personalDetails;
    private AddressDetails addressDetails;
    private BankDetails bankDetails;
    private ContactDetails contactDetails;

    @Getter
    @Setter
    public static class PersonalDetails {
        private String firstName;
        private String lastName;
        private String dob;
        private String gender;
        private String maritalStatus;
        private String bloodGroup;
        private String profilePic;
    }

    @Getter
    @Setter
    public static class AddressDetails {
        private String addressType;
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String state;
        private String postalCode;
        private String country;
    }

    @Getter
    @Setter
    public static class BankDetails {
        private String accountHolderName;
        private String bankName;
        private String branchName;
        private String accountNumber;
        private String ifscCode;
        private String panNo;
        private String uanNo;
    }

    @Getter
    @Setter
    public static class ContactDetails {
        private String phoneNumber;
        private String personalEmail;
    }
}

