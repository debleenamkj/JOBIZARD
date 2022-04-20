import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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

  public getMessages(senderId:any,recipientId:any) : Observable<ChatMessage[]>{
    return this.http.get<ChatMessage[]>("http://localhost:8089/messages/"+senderId+"/"+recipientId);
  }

  public getAllMessages() : Observable<ChatMessage[]>{
    return this.http.get<ChatMessage[]>("http://localhost:8089/getall");
  }

  public postMessages(chats:ChatMessage){
    return this.http.post<any>("http://localhost:8089/chat",chats);
  }

  public getChatroom() : Observable<ChatRoom[]>{
    return this.http.get<ChatRoom[]>("http://localhost:8089/getchatroom");
  }
}
