import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ChatMessage } from '../model/chat-message';
import { ChatRoom } from '../model/chat-room';

@Injectable({
  providedIn: 'root'
})
export class ChatroomService {

  senderId = localStorage.getItem('loginId');
  recipientId = "";
  senderName = "";
  recipientName = "";

  constructor(private http:HttpClient) { }
  baseUrl='https://jobizard.stackroute.io'+'/chatroom-service';
  // baseUrl="http://localhost:8089";

  public getMessages(senderId:any,recipientId:any) : Observable<ChatMessage[]>{
    return this.http.get<ChatMessage[]>(this.baseUrl+"/messages/"+senderId+"/"+recipientId);
  }

  public getAllMessages() : Observable<ChatMessage[]>{
    return this.http.get<ChatMessage[]>(this.baseUrl+"/getall");
  }

  public postMessages(chats:ChatMessage){
    return this.http.post<any>(this.baseUrl+"/chat",chats);
  }

  public getChatroom() : Observable<ChatRoom[]>{
    return this.http.get<ChatRoom[]>(this.baseUrl+"/getchatroom");
  }
}
