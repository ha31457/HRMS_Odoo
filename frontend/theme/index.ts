import rawTheme from "./themes.json";

export const theme = rawTheme as {
  colors: {
    background: {
      primary: string;
      secondary: string;
      muted: string;
    };
    text: {
      heading: string;
      body: string;
      muted: string;
      inverse: string;
    };
    border: {
      default: string;
      strong: string;
    };
    action: {
      primary: string;
      primaryHover: string;
      disabled: string;
    };
    status: {
      success: string;
      warning: string;
      error: string;
    };
  };
  typography: {
    fontFamily: {
      primary: string;
    };
    fontSize: {
      xs: string;
      sm: string;
      base: string;
      lg: string;
      xl: string;
      "2xl": string;
      "3xl": string;
    };
    fontWeight: {
      regular: number;
      medium: number;
      semibold: number;
      bold: number;
    };
    lineHeight: {
      tight: string;
      normal: string;
      relaxed: string;
    };
  };
};
