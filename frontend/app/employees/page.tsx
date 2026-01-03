"use client";

import { useState, useEffect } from "react";
import { theme } from "@/theme";
import Navbar from "@/components/layout/navbar";
import Footer from "@/components/layout/footer";
import EmployeeRegistrationForm from "@/components/forms/employeeRegistration";
import endpoints from "@/api/endpoints.json";

type Employee = {
  id: number;
  name: string;
  designation: string;
  department: string;
  photo: string;
};

const dummyEmployees: Employee[] = [
  {
    id: 1,
    name: "John Doe",
    designation: "Software Engineer",
    department: "Development",
    photo: "https://randomuser.me/api/portraits/men/32.jpg",
  },
  {
    id: 2,
    name: "Jane Smith",
    designation: "UI/UX Designer",
    department: "Design",
    photo: "https://randomuser.me/api/portraits/women/44.jpg",
  },
  {
    id: 3,
    name: "Alex Johnson",
    designation: "HR Manager",
    department: "Human Resources",
    photo: "https://randomuser.me/api/portraits/men/54.jpg",
  },
  {
    id: 4,
    name: "Alex Johnson",
    designation: "HR Manager",
    department: "Human Resources",
    photo: "https://randomuser.me/api/portraits/men/54.jpg",
  },
];

export default function EmployeesSection() {
  const [employees, setEmployees] = useState(dummyEmployees);
  const [searchTerm, setSearchTerm] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [loading, setLoading] = useState(false);


  const filteredEmployees = employees.filter((emp) =>
    emp.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  useEffect(() => {
    setLoading(true); // show loading if needed

    fetch(endpoints.GET_EMPLOYEES) // replace with your backend endpoint
        .then((res) => {
            if (!res.ok) throw new Error("Failed to fetch employees");
                return res.json();
            })
        .then((data: Employee[]) => {
            setEmployees(data); // populate state with backend data
        })
        .catch((err) => {
            console.error("Error fetching employees:", err);
        })
        .finally(() => {
            setLoading(false);
        });
    }, []);

  return (
    <div className="min-h-screen flex flex-col">
    <Navbar />

    <main className="flex-1">
        {loading ? (
        <div className="flex items-center justify-center h-full">
            <p>Loading employees...</p>
        </div>
        ) : (
        <div className="min-h-screen px-6 py-8 bg-gray-50" style={{ backgroundColor: theme.colors.background.muted }}>
        {/* Options Panel */}
        <div className="flex flex-col md:flex-row justify-between items-center mb-8 gap-4">
            <button
            onClick={() => setShowModal(true)}
            className="px-6 py-2 rounded-lg font-medium text-white duration-200 hover:scale-[1.01]"
            style={{ backgroundColor: theme.colors.action.primary, color: theme.colors.text.inverse }}
            >
            New Employee
            </button>

            <input
            type="text"
            placeholder="Search employees..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="border rounded-lg px-4 py-2 outline-none w-full md:w-90 transition-transform duration-300 hover:scale-[1.01]"
            style={{
                borderColor: theme.colors.border.default,
                color: theme.colors.text.heading,
                backgroundColor: theme.colors.background.primary,
            }}
            />
        </div>

        {/* Employees List */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-5 gap-6">
            {filteredEmployees.map((emp) => (
            <div
                key={emp.id}
                className="flex flex-col bg-white rounded-2xl shadow-md p-4 gap-4 transition-transform duration-200 hover:scale-[1.02]"
                style={{
                borderRadius: "16px",
                backgroundColor: theme.colors.background.primary,
                }}
            >
                <div className="flex gap-4 items-center">
                {/* Photo */}
                <img
                    src={emp.photo}
                    alt={emp.name}
                    className="w-16 h-16 rounded-full object-cover border"
                    style={{ borderColor: theme.colors.border.default }}
                />

                {/* Info */}
                <div className="flex flex-col">
                    <span className="font-semibold text-lg" style={{ color: theme.colors.text.heading }}>
                    {emp.name}
                    </span>
                    <span className="text-sm" style={{ color: theme.colors.text.body }}>
                    {emp.designation}
                    </span>
                    <span className="text-sm" style={{ color: theme.colors.text.muted }}>
                    {emp.department}
                    </span>
                </div>
                </div>
            </div>
            ))}
            {showModal && (
                <div className="fixed inset-0 flex items-center justify-center z-50" onClick={() => setShowModal(false)}>
                    <div className="absolute inset-0 bg-black opacity-50"></div>
                <div className="relative w-full max-w-2xl p-6" onClick={(e) => e.stopPropagation()}>
                    <EmployeeRegistrationForm />
                </div>
            </div>)}
        </div>
        </div>
            )}
        </main>
        <Footer />
      </div>
  );
}
