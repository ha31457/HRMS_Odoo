"use client";

import { useState } from "react";
import { theme } from "@/theme";

export default function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState<{ email?: string; password?: string }>({});

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const passwordRegex =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const newErrors: { email?: string; password?: string } = {};

    if (!email) newErrors.email = "Email is required";
    else if (!emailRegex.test(email)) newErrors.email = "Invalid email format";

    if (!password) newErrors.password = "Password is required";
    else if (!passwordRegex.test(password))
      newErrors.password =
        "Password must be at least 8 chars, 1 upper, 1 lower, 1 number, 1 special char";

    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      console.log("Submitting", { email, password });
    }
  };

  return (
    <div
  className="min-h-screen flex items-center justify-center bg-gray-50 px-4"
  style={{ backgroundColor: theme.colors.background.muted }}
>
  <div
    className="bg-white rounded-2xl shadow-xl p-10 flex flex-col gap-6"
    style={{
        borderRadius: "16px",
        padding: "30px",
        backgroundColor: theme.colors.background.primary,
        width: "400px", 
        maxWidth: "90%",
    }}
  >
    <h2
      className="text-xl font-semibold text-center"
      style={{ color: theme.colors.text.heading }}
    >
      Login
    </h2>

    <div className="flex flex-col gap-2">
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className="border rounded-md px-4 py-2 outline-none transition-all duration-200 ease-out focus:ring-2 focus:ring-green-500"
        style={{
            padding: "8px",
            borderRadius: "8px",
            borderColor: theme.colors.border.default,
            color: theme.colors.text.heading,
        }}
      />
      {errors.email && (
        <span className="text-xs" style={{ color: theme.colors.status.error }}>
          {errors.email}
        </span>
      )}
    </div>

    <div className="flex flex-col gap-2">
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className="border rounded-md px-4 py-2 outline-none transition-all duration-200 ease-out focus:ring-2 focus:ring-green-500"
        style={{
            padding: "8px",
            borderRadius: "8px",
            borderColor: theme.colors.border.default,
            color: theme.colors.text.heading,
        }}
      />
      {errors.password && (
        <span className="text-xs" style={{ color: theme.colors.status.error }}>
          {errors.password}
        </span>
      )}
    </div>

    <div className="flex justify-end">
      <a
        href="#"
        className="text-sm font-medium transition-colors duration-200 ease-out hover:underline"
        style={{ color: theme.colors.action.primary }}
      >
        Forgot password?
      </a>
    </div>

    <button
      type="submit"
      onClick={handleSubmit}
      className="w-full py-3 rounded-lg font-medium text-white transition-colors duration-200 ease-out hover:bg-green-600"
      style={{ backgroundColor: theme.colors.action.primary, color: theme.colors.text.inverse, padding: "8px", borderRadius: "8px" }}
    >
      Login
    </button>
  </div>
</div>

  );
}
