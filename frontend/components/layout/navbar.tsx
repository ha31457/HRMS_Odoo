"use client";

import Link from "next/link";
import { theme } from "@/theme";

const NAV_ITEMS = [
  { label: "Employees", href: "/employees" },
  { label: "Attendance", href: "/attendance" },
  { label: "Time Off", href: "/time-off" },
];

export default function Navbar() {
  return (
    <nav
      className="w-full border-b"
      style={{
        backgroundColor: theme.colors.background.primary,
        borderColor: theme.colors.border.default,
      }}
    >
      <div className="mx-auto max-w-7xl px-6">
        <div className="flex h-16 items-center justify-between">
          
          <div className="flex items-center gap-10">
            <Link href="/">
                <span
                className="text-xl font-semibold"
                style={{
                    color: theme.colors.text.heading,
                    fontWeight: theme.typography.fontWeight.semibold,
                    }}
                    
                    >
                HRMS
                </span>
            </Link>

            <ul className="flex items-center gap-6">
              {NAV_ITEMS.map((item) => (
                <li key={item.label}>
                  <Link
                    href={item.href}
                    className="text-sm transition-colors"
                    style={{
                      color: theme.colors.text.body,
                      fontWeight: theme.typography.fontWeight.medium,
                    }}
                  >
                    {item.label}
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          <div className="flex items-center gap-4">
            <button
                className="flex h-10 w-10 items-center justify-center rounded-full border
                            transition-all duration-200 ease-out
                            hover:-translate-y-[1px]"
                
                style={{
                    borderColor: "red",
                    backgroundColor: "red",
                    color: "white"
                }}
                onMouseEnter={(e) => {
                    e.currentTarget.style.backgroundColor = "green";
                    e.currentTarget.style.color = theme.colors.text.inverse;
                    e.currentTarget.style.borderColor = "green";
                }}
                onMouseLeave={(e) => {
                    e.currentTarget.style.backgroundColor = "red";
                    e.currentTarget.style.borderColor = "red";
                    e.currentTarget.style.color = "white";
                }}
                >
                ✓
            </button>


            <button
                className="h-9 w-9 rounded-full text-sm transition-all duration-200 ease-out
                            hover:-translate-y-[1px] hover:ring-1 hover:ring-offset-2"
                style={{
                    backgroundColor: theme.colors.action.primary,
                    color: theme.colors.text.inverse
                }}
                >
                HP
                </button>

          </div>
        </div>
      </div>
    </nav>
  );
}
