import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { LoginLayoutComponent } from './dcc-layout-login/login-layout.component';
import { AppLayoutComponent } from './dcc-layout-app/app-layout.component';
import { SideNavComponent } from './side-nav/side-nav.component';



export const layout: any[] = [FooterComponent, HeaderComponent, AppLayoutComponent, LoginLayoutComponent, SideNavComponent];

export * from './footer/footer.component';
export * from './header/header.component';
export * from './dcc-layout-login/login-layout.component';
export * from './dcc-layout-app/app-layout.component';
export * from './side-nav/side-nav.component';
