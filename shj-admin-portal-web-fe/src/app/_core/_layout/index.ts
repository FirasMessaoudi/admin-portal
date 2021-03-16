import { FooterComponent } from './footer/footer.component';
import { ScrollToTopComponent } from './scroll-to-top/scroll-to-top.component';
import { HeaderComponent } from './header/header.component';
import { LoginLayoutComponent } from './dcc-layout-login/login-layout.component';
import { AppLayoutComponent } from './dcc-layout-app/app-layout.component';



export const layout: any[] = [FooterComponent, HeaderComponent, ScrollToTopComponent, AppLayoutComponent, LoginLayoutComponent];

export * from './footer/footer.component';
export * from './scroll-to-top/scroll-to-top.component';
export * from './header/header.component';
export * from './dcc-layout-login/login-layout.component';
export * from './dcc-layout-app/app-layout.component';
