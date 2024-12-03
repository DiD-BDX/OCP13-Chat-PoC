import { Routes } from '@angular/router';
import { ChatComponent } from './components/chat/chat.component';

export const routes: Routes = [
    { path: 'chat', component: ChatComponent },
    { path: '', redirectTo: '/chat', pathMatch: 'full' }
];