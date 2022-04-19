import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ChatMessage } from '../model/chat-message';
import { ChatRoom } from '../model/chat-room';
import { ChatroomService } from '../service/chatroom.service';



@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.css']
})
export class ChatroomComponent implements OnInit {

  getChats:ChatMessage[]=[];
  getAllChats:ChatMessage[]=[];

  sendChats: ChatMessage = new ChatMessage;


  getChatRoom:ChatRoom[]=[];
  //receiverNames:any[]=[];
  receiverNames:ChatMessage[]=[];
  
  messages!:string[];
  text:any;

  receiverInitials!:string;

  today= new Date();
  jstoday = '';


  constructor(private chatService:ChatroomService) {

    this.jstoday = formatDate(this.today, 'dd-MM-yyyy hh:mm:ss a', 'en-US', '+0530');

    console.log(this.jstoday);
    
    
   }

  ngOnInit(): void {
    this.chatService.getMessages(this.senderId,this.recipientId).subscribe((data)=>{
      this.getChats = data;
      this.getChats.reverse();
      this.receiverInitials = this.recipientName.charAt(0);
    })

    this.receiverInitials = this.recipientName.charAt(0);

    this.chatService.getAllMessages().subscribe((data)=>{
     var res: any[] = [];
   data.filter(function(item){
      var i = res.findIndex(x=>x.recipientId == item.recipientId);
      if(i<=-1){
        res.push(item);
      }
      return null;
    });


    this.receiverNames = res;

    // console.log(this.receiverNames);
    // this.receiverNames.forEach((element,index)=>{
    //       if(element.senderId!=this.senderId) {this.receiverNames.splice(index,1);}

    console.log(this.receiverNames);
    // this.receiverNames.forEach((element,index)=>{
    //       if(element.senderId===this.senderId) {this.receiverNames.splice(index,1);}

    //    });
    })

    
    
    this.chatService.getChatroom().subscribe((data)=>{
      this.getChatRoom = data;
      console.log(data);
      console.log(data.filter((e)=>e.senderId == this.senderId));
      console.log(data.map((e)=>e.chatId));
      
      data.filter((e)=>e.senderId == this.senderId);
      
    })
    
  }
  

  senderId = this.chatService.senderId;
  recipientId = this.chatService.recipientId;
  senderName = this.chatService.senderName;
  recipientName = this.chatService.recipientName;

  // senderId = "S123"
  // recipientId = "R124"
  // senderName = "ABCD";
  // recipientName = "IJKL";


  // senderId = "S123"
  // recipientId = "R123"
  // senderName = "ABCD";
  // recipientName = "EFGH";

  // senderId = "R124";
  // recipientId = "S123"
  // senderName = "IJKL";
  // recipientName = "ABCD";


  // senderId = "R124";
  // recipientId = "R123"
  // senderName = "IJKL";
  // recipientName = "EFGH";


  sendMessage(){

    console.log(this.text);
    this.sendChats.senderId = this.senderId;    
    this.sendChats.senderName = this.senderName;
    this.sendChats.recipientId = this.recipientId;
    this.sendChats.recipientName = this.recipientName;
    this.sendChats.timestamp = this.jstoday;
    this.sendChats.message = this.text;
    console.log(this.sendChats);
    
    this.chatService.postMessages(this.sendChats).subscribe((data)=>{
      console.log(data);
      console.log(this.sendChats);
      this.text = "";
      this.ngOnInit();
    })
    
  }


  selectedReceiver(receiver:any){
    console.log(receiver);
    this.recipientName = receiver.receiverNames;
    this.chatService.getMessages(this.senderId,receiver.recipientId).subscribe((data)=>{
      this.getChats = data;
      this.getChats.reverse();
      this.recipientName = receiver.recipientName;
      this.receiverInitials = receiver.recipientName.charAt(0);
    })
    
  }

  chatMethod(senderId:string,senderName:string,receiverId:string,receiverName:string){
    this.sendChats.senderId = senderId;    
    this.sendChats.senderName = senderName;
    this.sendChats.recipientId = receiverId;
    this.sendChats.recipientName = receiverName;
    this.chatService.getMessages(senderId,receiverId).subscribe((data)=>{
      this.getChats = data;
      this.getChats.reverse();
      this.recipientName = receiverName;
      this.receiverInitials = receiverName.charAt(0);
    })
    this.ngOnInit();
    
  }
}
