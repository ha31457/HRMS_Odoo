import Image from "next/image";
import Navbar from "@/components/layout/navbar";
import Footer from "@/components/layout/footer";

export default function Home() {
  return (
  <div className="min-h-screen flex flex-col">
    <Navbar />

    <main className="flex-1">
      {/* Render content here */}
    </main>

    <Footer />
  </div>
);

}
