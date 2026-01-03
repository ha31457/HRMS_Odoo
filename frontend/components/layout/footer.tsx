import { theme } from "@/theme";

export default function Footer() {
  return (
    <footer
      className="w-full border-t"
      style={{
        backgroundColor: theme.colors.background.primary,
        borderColor: theme.colors.border.default,
      }}
    >
      <div className="mx-auto max-w-7xl px-6 py-4">
        <p
          className="text-center text-sm"
          style={{
            color: theme.colors.text.muted,
            fontSize: theme.typography.fontSize.sm,
          }}
        >
          © {new Date().getFullYear()} HRMS. All rights reserved.
        </p>
      </div>
    </footer>
  );
}
