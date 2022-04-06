import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatMessage } from '../model/chat-message';
import { ChatRoom } from '../model/chat-room';

@Injectable({
  providedIn: 'root'
})
export class ChatroomService {

  constructor(private http:HttpClient) { }

  public getMessages() : Observable<ChatMessage[]>{
    return this.http.get<ChatMessage[]>("http://localhost:8090/getall");
  }

  public postMessages(chats:ChatMessage){
    return this.http.postImage<any>("http://localhost:8090/chat",chats);
  }

  public getChatroom() : Observable<ChatRoom[]>{
    return this.http.get<ChatRoom[]>("http://localhost:8090/getchatroom");
  }
}
