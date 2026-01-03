import Image from "next/image";
import Navbar from "@/components/layout/navbar";
import Footer from "@/components/layout/footer";
import LoginForm from "@/components/auth/loginform";
import EmployeeRegistrationForm from "@/components/forms/employeeRegistration";

export default function Home() {
  return (
    <div className="min-h-screen flex flex-col">
    <Navbar />

    <main className="flex-1">
    <EmployeeRegistrationForm />
    </main>

    <Footer />
  </div>
);

}
