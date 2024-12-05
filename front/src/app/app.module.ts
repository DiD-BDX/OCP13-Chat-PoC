import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { WebsocketService } from './services/websocket.service';
import { routes } from './app.routes';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user.service';

import { AppComponent } from './app.component';
import { ChatComponent } from './components/chat/chat.component';
import { LoginComponent } from './components/login/login.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        ChatComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(routes)
    ],
    providers: [WebsocketService, UserService],
    bootstrap: [AppComponent]
})
export class AppModule { }