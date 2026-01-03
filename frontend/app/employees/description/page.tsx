"use client";

import { useState, useEffect } from "react";
import { theme } from "@/theme";
import endpoints from "@/api/endpoints.json";
import Navbar from "@/components/layout/navbar";
import Footer from "@/components/layout/footer";

type EmployeeProfileData = {
  name: string;
  jobPosition: string;
  email: string;
  phone: string;
  company: string;
  department: string;
  manager: string;
  location: string;
  photoUrl: string;

  privateInfo: {
    dob: string;
    address: string;
    nationality: string;
    personalEmail: string;
    gender: string;
    maritalStatus: string;
    joiningDate: string;
    bloodGroup: string;
  };

  bankInfo: {
    accountHolder: string;
    accountNumber: string;
    bankName: string;
    branchName: string;
    ifsc: string;
    pan: string;
    uan: string;
    employeeCode: string;
  };

  salaryInfo: {
    monthly: string;
    yearly: string;
    workingDays: string;
    breakTime: string;
  };
};

const dummyProfile: EmployeeProfileData = {
  "name": "Aarav Mehta",
  "jobPosition": "Senior Software Engineer",
  "email": "aarav.mehta@company.com",
  "phone": "+91 98765 43210",
  "company": "TechNova Solutions",
  "department": "Engineering",
  "manager": "Neha Sharma",
  "location": "Ahmedabad, Gujarat, India",
  "photoUrl": "https://randomuser.me/api/portraits/men/45.jpg",

  "privateInfo": {
    "dob": "14 Feb 1996",
    "address": "Satellite, Ahmedabad, Gujarat",
    "nationality": "Indian",
    "personalEmail": "aarav.mehta@gmail.com",
    "gender": "Male",
    "maritalStatus": "Single",
    "joiningDate": "10 March 2021",
    "bloodGroup": "B+"
  },

  "bankInfo": {
    "accountHolder": "Aarav Mehta",
    "accountNumber": "XXXXXXXX7890",
    "bankName": "HDFC Bank",
    "branchName": "Satellite Branch",
    "ifsc": "HDFC0000789",
    "pan": "BQTPM1234K",
    "uan": "123456789012",
    "employeeCode": "EMP-1045"
  },

  "salaryInfo": {
    "monthly": "₹85,000",
    "yearly": "₹10,20,000",
    "workingDays": "5",
    "breakTime": "1 Hour"
  }
}



export default function EmployeeProfile() {
  const [activeTab, setActiveTab] = useState<"private" | "salary">("private");
  const [employee, setEmployee] = useState<EmployeeProfileData | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    // fetch(endpoints.GET_PROFILE, { method: "GET", headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` } })
    //     .then((res) => {
    //     if (!res.ok) {
    //         throw new Error("Failed to fetch employee data");
    //     }
    //     return res.json();
    //     })
    //     .then((data) => {
    //     setEmployee(data);
    //     setLoading(false);
    //     })
    //     .catch((err) => {
    //     setError(err.message);
    //     setLoading(false);
    //     });
        setLoading(false);
        setEmployee(dummyProfile);
    }, []);


    if (loading) {
        return (
            <div className="min-h-screen flex items-center justify-center">
            Loading profile...
            </div>
        );
        }

    if (error) {
        return (
            <div className="min-h-screen flex items-center justify-center text-red-500">
            {error}
            </div>
        );
    }

    if(employee) {
        
    return (
        <div className="min-h-screen flex flex-col">
            <Navbar />
        
            <main className="flex-1">

            <div
            className="min-h-screen px-6 py-8"
            style={{ backgroundColor: theme.colors.background.muted }}
            >
            <div
                className="max-w-8xl mx-auto rounded-2xl shadow-xl p-8"
                style={{ backgroundColor: theme.colors.background.primary }}
            >
                <h1
                className="text-2xl font-semibold"
                style={{ color: theme.colors.text.heading }}
                >
                My Profile
                </h1>

                <hr
                className="my-6"
                style={{ borderColor: theme.colors.border.default }}
                />

                <div className="flex gap-6 items-start">
                <img
                    src="https://randomuser.me/api/portraits/men/32.jpg"
                    alt="Employee"
                    className="w-24 h-24 rounded-full object-cover border"
                    style={{ borderColor: theme.colors.border.default }}
                />

                <div className="grid grid-cols-2 gap-y-2 flex-1">
                    {[
                    ["Name", employee.name],
                    ["Job Position", employee.jobPosition],
                    ["Email", employee.email],
                    ["Phone", employee.phone],
                    ["Company", employee.company],
                    ["Department", employee.department],
                    ["Manager", employee.manager],
                    ["Location", employee.location],

                    ].map(([label, value]) => (
                        <InfoRow key={label} label={label} value={value} />
                    ))}
                </div>
                </div>

                <div className="flex gap-6 mt-10 border-b">
                {[
                    { key: "private", label: "Private Info" },
                    { key: "salary", label: "Salary Info" },
                ].map((tab) => (
                    <button
                    key={tab.key}
                    onClick={() => setActiveTab(tab.key as any)}
                    className="pb-3 font-medium transition-colors"
                    style={{
                        borderBottom:
                        activeTab === tab.key
                            ? `2px solid ${theme.colors.action.primary}`
                            : "2px solid transparent",
                        color:
                        activeTab === tab.key
                            ? theme.colors.text.heading
                            : theme.colors.text.muted,
                    }}
                    >
                    {tab.label}
                    </button>
                ))}
                </div>

                {activeTab === "private" && (
                <div className="grid grid-cols-2 gap-10 mt-8">
                    <div className="space-y-4">
                    {[
                        ["DOB", employee.privateInfo.dob],
                        ["Residing Address", employee.privateInfo.address],
                        ["Nationality", employee.privateInfo.nationality],
                        ["Personal Email", employee.privateInfo.personalEmail],
                        ["Gender", employee.privateInfo.gender],
                        ["Marital Status", employee.privateInfo.maritalStatus],
                        ["Date of Joining", employee.privateInfo.joiningDate],
                        ["Blood Group", employee.privateInfo.bloodGroup],
                                            
                    ].map(([label, value]) => (
                        <InfoRow key={label} label={label} value={value} />
                    ))}
                    </div>

                    <div className="space-y-4">
                    {[
                        ["Account Holder", employee.bankInfo.accountHolder],
                        ["Account Number", employee.bankInfo.accountNumber],
                        ["Bank Name", employee.bankInfo.bankName],
                        ["Branch Name", employee.bankInfo.branchName],
                        ["IFSC Code", employee.bankInfo.ifsc],
                        ["PAN No", employee.bankInfo.pan],
                        ["UAN No", employee.bankInfo.uan],
                        ["Employee Code", employee.bankInfo.employeeCode],

                    ].map(([label, value]) => (
                        <InfoRow key={label} label={label} value={value} />
                    ))}
                    </div>
                </div>
                )}

                {activeTab === "salary" && (
                <div className="mt-8 space-y-10">
                    <div className="grid grid-cols-2 gap-10">
                    <div className="space-y-4">
                        <InfoRow label="Monthly Wage" value={employee.salaryInfo.monthly} />
                        <InfoRow label="Yearly Wage" value={employee.salaryInfo.yearly} />

                    </div>

                    <div className="space-y-4">
                        <InfoRow label="Working Days / Week" value={employee.salaryInfo.workingDays} />
                        <InfoRow label="Break Time" value={employee.salaryInfo.breakTime} />
                    </div>
                    </div>

                    <div className="grid grid-cols-2 gap-10">
                    <div className="space-y-4">
                        <SectionTitle title="Salary Components" />
                        {[
                        ["Basic Salary", "₹25,000"],
                        ["HRA", "₹10,000"],
                        ["Standard Allowance", "₹5,000"],
                        ["Performance Bonus", "₹5,000"],
                        ["Leave Travel Allowance", "₹3,000"],
                        ["Fixed Allowance", "₹2,000"],
                        ].map(([label, value]) => (
                        <InfoRow key={label} label={label} value={value} />
                        ))}
                    </div>

                    <div className="space-y-6">
                        <div>
                        <SectionTitle title="PF Contribution" />
                        <InfoRow label="Employee" value="₹1,800" />
                        <InfoRow label="Employer" value="₹1,800" />
                        </div>

                        <div>
                        <SectionTitle title="Tax Deductions" />
                        <InfoRow label="Professional Tax" value="₹200" />
                        </div>
                    </div>
                    </div>
                </div>
                )}
            </div>
            </div>
            </main>
        
            <Footer />
          </div>
        );
    }

}

function InfoRow({ label, value }: { label: string; value: string }) {
  return (
    <div>
      <p className="text-sm" style={{ color: theme.colors.text.body }}>
        {label + ": "}
      <span className="font-medium" style={{ color: theme.colors.text.heading }}>
        {value}
      </span>
    </p>
  </div>
  );
}

function SectionTitle({ title }: { title: string }) {
  return (
    <h3
      className="font-semibold text-lg mb-2"
      style={{ color: theme.colors.text.heading }}
    >
      {title}
    </h3>
  );
}
 