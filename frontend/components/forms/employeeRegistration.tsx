"use client";

import { useState } from "react";
import { theme } from "@/theme";

type PersonalDetails = {
  firstName: string;
  lastName: string;
  dob: string;
  gender: string;
  maritalStatus: string;
  bloodGroup: string;
  profilePic: string;
};

type AddressDetails = {
  addressType: string;
  addressLine1: string;
  addressLine2: string;
  city: string;
  state: string;
  postalCode: string;
  country: string;
};

type BankDetails = {
  accountHolderName: string;
  bankName: string;
  branchName: string;
  accountNumber: string;
  ifscCode: string;
  panNo: string;
  uanNo: string;
};

type ContactDetails = {
  phoneNumber: string;
  personalEmail: string;
};

type FormData = {
  personalDetails: PersonalDetails;
  addressDetails: AddressDetails;
  bankDetails: BankDetails;
  contactDetails: ContactDetails;
};


export default function EmployeeRegistrationForm() {
  const [currentTab, setCurrentTab] = useState<"personal" | "address" | "bank">(
    "personal"
  );

  const [formData, setFormData] = useState<FormData>({
    personalDetails: {
        firstName: "",
        lastName: "",
        dob: "",
        gender: "",
        maritalStatus: "",
        bloodGroup: "",
        profilePic: "",
    },
    addressDetails: {
        addressType: "",
        addressLine1: "",
        addressLine2: "",
        city: "",
        state: "",
        postalCode: "",
        country: "",
    },
    bankDetails: {
        accountHolderName: "",
        bankName: "",
        branchName: "",
        accountNumber: "",
        ifscCode: "",
        panNo: "",
        uanNo: "",
    },
    contactDetails: {
        phoneNumber: "",
        personalEmail: "",
    },
  });

  const [errors, setErrors] = useState<any>({});

  // Regex
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const phoneRegex = /^[0-9]{10}$/;
  const bloodGroupRegex = /^(A|B|AB|O)[+-]$/;
  const urlRegex = /^(https?:\/\/.*\.(?:png|jpg|jpeg|gif|svg|webp))$/i;
  const ifscRegex = /^[A-Z]{4}0[A-Z0-9]{6}$/;
  const panRegex = /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/;
  const uanRegex = /^[0-9]{12}$/;

  const handleTabClick = (tab: "personal" | "address" | "bank") => {
    setCurrentTab(tab);
  };

  const validateForm = () => {
    const newErrors: any = {};

    // Personal
    const pd = formData.personalDetails;
    if (!pd.firstName) newErrors.firstName = "First name is required";
    if (!pd.lastName) newErrors.lastName = "Last name is required";
    if (!pd.dob) newErrors.dob = "Date of birth is required";
    if (!pd.gender) newErrors.gender = "Gender is required";
    if (!pd.maritalStatus) newErrors.maritalStatus = "Marital status required";
    if (!pd.bloodGroup) newErrors.bloodGroup = "Blood group required";
    else if (!bloodGroupRegex.test(pd.bloodGroup))
      newErrors.bloodGroup = "Invalid blood group";
    if (pd.profilePic && !urlRegex.test(pd.profilePic))
      newErrors.profilePic = "Invalid image URL";

    // Address
    const ad = formData.addressDetails;
    if (!ad.addressType) newErrors.addressType = "Address type is required";
    if (!ad.addressLine1) newErrors.addressLine1 = "Address Line 1 required";
    if (!ad.city) newErrors.city = "City required";
    if (!ad.state) newErrors.state = "State required";
    if (!ad.postalCode) newErrors.postalCode = "Postal code required";
    if (!ad.country) newErrors.country = "Country required";

    // Bank
    const bd = formData.bankDetails;
    if (!bd.accountHolderName) newErrors.accountHolderName = "Required";
    if (!bd.bankName) newErrors.bankName = "Required";
    if (!bd.branchName) newErrors.branchName = "Required";
    if (!bd.accountNumber) newErrors.accountNumber = "Required";
    if (!bd.ifscCode) newErrors.ifscCode = "Required";
    else if (!ifscRegex.test(bd.ifscCode)) newErrors.ifscCode = "Invalid IFSC";
    if (!bd.panNo) newErrors.panNo = "Required";
    else if (!panRegex.test(bd.panNo)) newErrors.panNo = "Invalid PAN";
    if (!bd.uanNo) newErrors.uanNo = "Required";
    else if (!uanRegex.test(bd.uanNo)) newErrors.uanNo = "Invalid UAN";

    // Contact
    const cd = formData.contactDetails;
    if (!cd.phoneNumber) newErrors.phoneNumber = "Phone number is required";
    else if (!phoneRegex.test(cd.phoneNumber))
      newErrors.phoneNumber = "Phone number must be 10 digits";
    if (!cd.personalEmail) newErrors.personalEmail = "Email is required";
    else if (!emailRegex.test(cd.personalEmail))
      newErrors.personalEmail = "Invalid email format";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (validateForm()) {
      console.log("Employee Data:", formData);
      alert("Form submitted! Check console for JSON.");
    }
  };

  return (
    <div
      className="min-h-screen flex flex-col items-center justify-center px-4"
    >
      <div
        className="bg-white rounded-2xl shadow-xl p-8 flex flex-col gap-6 max-w-4xl max-h-[80vh] overflow-y-auto scrollbar-themed"
        style={{
            borderRadius: "16px",
            padding: "30px",
            width: "600px", 
            maxWidth: "90%",
            backgroundColor: theme.colors.background.primary,
        }}
        
      >
        <style jsx>{`
            div::-webkit-scrollbar {
            width: 8px;
            }
            div::-webkit-scrollbar-track {
            background: #373D20;
            border-radius: 8px;
            }
            div::-webkit-scrollbar-thumb {
            background-color: #373D20;
            border-radius: 8px;
            }
            div::-webkit-scrollbar-thumb:hover {
            background-color: #2C3117;
            }
        `}</style>
        <h2 className="text-xl font-semibold text-center" style={{ color: theme.colors.text.heading }}>Registration Form</h2>
        {/* Tabs */}
        <div className="flex gap-4 border-b pb-2 mb-6" style={{ borderColor: theme.colors.border.default }}>
          {["personal", "address", "bank"].map((tab) => (
            <button
              key={tab}
              onClick={() =>
                handleTabClick(tab as "personal" | "address" | "bank")
              }
              className={`px-4 py-2 font-medium transition-colors duration-200 ${
                currentTab === tab
                  ? "border-b-2"
                  : "text-text.body hover:text-action.primary"
              }`}
              style={{
                borderColor:
                  currentTab === tab
                    ? theme.colors.action.primary
                    : "transparent",
                color:
                  currentTab === tab
                    ? theme.colors.text.heading
                    : theme.colors.text.body,
              }}
            >
              {tab.charAt(0).toUpperCase() + tab.slice(1)}
            </button>
          ))}
        </div>

        <form className="flex flex-col gap-4" onSubmit={handleSubmit}>
          {currentTab === "personal" && (
            <div className="flex flex-col gap-4">
              {/* First & Last Name */}
              <div className="flex gap-4">
                {["firstName", "lastName"].map((field, i) => (
                  <div className="flex flex-col flex-1" key={field}>
                    <label className="text-text.heading">
                      {field === "firstName" ? "First Name" : "Last Name"}
                    </label>
                    <input
                      type="text"
                      value={
                        formData.personalDetails[
                          field as keyof PersonalDetails
                        ]
                      }
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          personalDetails: {
                            ...formData.personalDetails,
                            [field]: e.target.value,
                          },
                        })
                      }
                      className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                      style={{
                        borderColor: theme.colors.border.default,
                        color: theme.colors.text.heading,
                      }}
                    />
                    {errors[field] && (
                      <span className="text-xs" style={{ color: theme.colors.status.error }}>
                        {errors[field]}
                      </span>
                    )}
                  </div>
                ))}
              </div>

              {/* Phone, Email */}
              <div className="flex gap-4">
                {["phoneNumber", "personalEmail"].map((field) => (
                  <div className="flex flex-col flex-1" key={field}>
                    <label className="text-text.heading">
                      {field === "phoneNumber" ? "Phone Number" : "Email"}
                    </label>
                    <input
                      type={field === "personalEmail" ? "email" : "text"}
                      value={
                        formData.contactDetails[
                          field as keyof ContactDetails
                        ]
                      }
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          contactDetails: {
                            ...formData.contactDetails,
                            [field]: e.target.value,
                          },
                        })
                      }
                      className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                      style={{
                        borderColor: theme.colors.border.default,
                        color: theme.colors.text.heading,
                      }}
                    />
                    {errors[field] && (
                      <span className="text-xs" style={{ color: theme.colors.status.error }}>
                        {errors[field]}
                      </span>
                    )}
                  </div>
                ))}
              </div>

              {/* DOB, Gender, Marital, Blood Group, Profile Pic */}
              <div className="flex flex-col gap-4">
                <div className="flex gap-4">
                  <div className="flex flex-col flex-1">
                    <label className="text-text.heading">DOB</label>
                    <input
                      type="date"
                      value={formData.personalDetails.dob}
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          personalDetails: {
                            ...formData.personalDetails,
                            dob: e.target.value,
                          },
                        })
                      }
                      className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                      style={{
                        borderColor: theme.colors.border.default,
                        color: theme.colors.text.heading,
                      }}
                    />
                    {errors.dob && (
                      <span className="text-xs" style={{ color: theme.colors.status.error }}>
                        {errors.dob}
                      </span>
                    )}
                  </div>

                  <div className="flex flex-col flex-1">
                    <label className="text-text.heading">Gender</label>
                    <select
                      value={formData.personalDetails.gender}
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          personalDetails: {
                            ...formData.personalDetails,
                            gender: e.target.value,
                          },
                        })
                      }
                      className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                      style={{
                        borderColor: theme.colors.border.default,
                        color: theme.colors.text.heading,
                      }}
                    >
                      <option style={{ backgroundColor: theme.colors.background.primary }} value="">Select</option>
                      <option style={{ backgroundColor: theme.colors.background.primary }}value="Male">Male</option>
                      <option style={{ backgroundColor: theme.colors.background.primary }}value="Female">Female</option>
                      <option style={{ backgroundColor: theme.colors.background.primary }}value="Other">Other</option>
                    </select>
                    {errors.gender && (
                      <span className="text-xs" style={{ color: theme.colors.status.error }}>
                        {errors.gender}
                      </span>
                    )}
                  </div>
                </div>

                {/* Marital & Blood Group */}
                <div className="flex gap-4">
                  <div className="flex flex-col flex-1">
                    <label className="text-text.heading">Marital Status</label>
                    <select
                      value={formData.personalDetails.maritalStatus}
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          personalDetails: {
                            ...formData.personalDetails,
                            maritalStatus: e.target.value,
                          },
                        })
                      }
                      className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                      style={{
                        borderColor: theme.colors.border.default,
                        color: theme.colors.text.heading,
                      }}
                    >
                      <option style={{ backgroundColor: theme.colors.background.primary }} value="">Select</option>
                      <option style={{ backgroundColor: theme.colors.background.primary }} value="Single">Single</option>
                      <option style={{ backgroundColor: theme.colors.background.primary }} value="Married">Married</option>
                      <option style={{ backgroundColor: theme.colors.background.primary }} value="Divorced">Divorced</option>
                      <option style={{ backgroundColor: theme.colors.background.primary }} value="Widowed">Widowed</option>
                    </select>
                    {errors.maritalStatus && (
                      <span className="text-xs" style={{ color: theme.colors.status.error }}>
                        {errors.maritalStatus}
                      </span>
                    )}
                  </div>

                  <div className="flex flex-col flex-1">
                    <label className="text-text.heading">Blood Group</label>
                    <input
                      type="text"
                      placeholder="A+, O-, etc."
                      value={formData.personalDetails.bloodGroup}
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          personalDetails: {
                            ...formData.personalDetails,
                            bloodGroup: e.target.value,
                          },
                        })
                      }
                      className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                      style={{
                        borderColor: theme.colors.border.default,
                        color: theme.colors.text.heading,
                      }}
                    />
                    {errors.bloodGroup && (
                      <span className="text-xs" style={{ color: theme.colors.status.error }}>
                        {errors.bloodGroup}
                      </span>
                    )}
                  </div>
                </div>

                {/* Profile Pic */}
                <div className="flex flex-col">
                  <label className="text-text.heading">Profile Picture URL</label>
                  <input
                    type="text"
                    placeholder="https://..."
                    value={formData.personalDetails.profilePic}
                    onChange={(e) =>
                      setFormData({
                        ...formData,
                        personalDetails: {
                          ...formData.personalDetails,
                          profilePic: e.target.value,
                        },
                      })
                    }
                    className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                    style={{
                      borderColor: theme.colors.border.default,
                      color: theme.colors.text.heading,
                    }}
                  />
                  {errors.profilePic && (
                    <span className="text-xs" style={{ color: theme.colors.status.error }}>
                      {errors.profilePic}
                    </span>
                  )}
                </div>
              </div>
            </div>
          )}

          {currentTab === "address" && (
            <div className="flex flex-col gap-4">
              {/* Address fields */}
              {[
                { label: "Address Type", key: "addressType", type: "select", options: ["Current", "Permanent"] },
                { label: "Address Line 1", key: "addressLine1" },
                { label: "Address Line 2", key: "addressLine2" },
                { label: "City", key: "city" },
                { label: "State", key: "state" },
                { label: "Postal Code", key: "postalCode" },
                { label: "Country", key: "country" },
              ].map((field) => (
                <div className="flex flex-col" key={field.key}>
                  <label className="text-text.heading">{field.label}</label>
                  {field.type === "select" ? (
                    <select
                      value={
                        formData.addressDetails[
                          field.key as keyof AddressDetails
                        ]
                      }
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          addressDetails: {
                            ...formData.addressDetails,
                            [field.key]: e.target.value,
                          },
                        })
                      }
                      className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                      style={{
                        borderColor: theme.colors.border.default,
                        color: theme.colors.text.heading,
                      }}
                    >
                      <option value="" style={{ backgroundColor: theme.colors.background.primary }}>Select</option>
                      {field.options?.map((o) => (
                        <option key={o} value={o} style={{ backgroundColor: theme.colors.background.primary }}>
                          {o}
                        </option>
                      ))}
                    </select>
                  ) : (
                    <input
                      type="text"
                      value={
                        formData.addressDetails[
                          field.key as keyof AddressDetails
                        ]
                      }
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          addressDetails: {
                            ...formData.addressDetails,
                            [field.key]: e.target.value,
                          },
                        })
                      }
                      className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                      style={{
                        borderColor: theme.colors.border.default,
                        color: theme.colors.text.heading,
                      }}
                    />
                  )}
                  {errors[field.key] && (
                    <span className="text-xs" style={{ color: theme.colors.status.error }}>
                      {errors[field.key]}
                    </span>
                  )}
                </div>
              ))}
            </div>
          )}

          {currentTab === "bank" && (
            <div className="flex flex-col gap-4">
              {[
                { label: "Account Holder Name", key: "accountHolderName" },
                { label: "Bank Name", key: "bankName" },
                { label: "Branch Name", key: "branchName" },
                { label: "Account Number", key: "accountNumber" },
                { label: "IFSC Code", key: "ifscCode" },
                { label: "PAN Number", key: "panNo" },
                { label: "UAN Number", key: "uanNo" },
              ].map((field) => (
                <div className="flex flex-col" key={field.key}>
                  <label className="text-text.heading">{field.label}</label>
                  <input
                    type="text"
                    value={formData.bankDetails[field.key as keyof BankDetails]}
                    onChange={(e) =>
                      setFormData({
                        ...formData,
                        bankDetails: {
                          ...formData.bankDetails,
                          [field.key]: e.target.value,
                        },
                      })
                    }
                    className="border px-4 py-2 rounded-md focus:ring-2 focus:ring-action.primary"
                    style={{
                      borderColor: theme.colors.border.default,
                      color: theme.colors.text.heading,
                    }}
                  />
                  {errors[field.key] && (
                    <span className="text-xs" style={{ color: theme.colors.status.error }}>
                      {errors[field.key]}
                    </span>
                  )}
                </div>
              ))}
            </div>
          )}

          <button
            type="submit"
            className="mt-6 py-3 rounded-lg font-medium text-white transition-colors duration-200 ease-out hover:bg-green-600"
            style={{
              backgroundColor: theme.colors.action.primary,
              color: theme.colors.text.inverse,
            }}
          >
            Submit
          </button>
        </form>
      </div>
    </div>
  );
}
