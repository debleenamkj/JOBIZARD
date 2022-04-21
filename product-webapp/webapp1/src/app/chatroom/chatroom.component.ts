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
   stringArray:any[]=[]; 
  ngOnInit(): void {
    this.chatService.getMessages(this.senderId,this.recipientId).subscribe((data)=>{
      this.getChats = data;
      this.getChats.reverse();
      this.receiverInitials = this.recipientId.charAt(0);
    })

    this.receiverInitials = this.recipientName.charAt(0);

    this.chatService.getAllMessages().subscribe((data)=>{
        console.log(data);
          
      // let 
      data.filter((e)=> e.senderId == this.senderId || e.recipientId == this.senderId).forEach((e)=>{
        
        if(!this.stringArray.includes(e.chatId)){
         
          
          this.stringArray.push(e.chatId);
          this.receiverNames.push(e);
        }
      });
      
     
      

  //    var res: any[] = [];
  //  data.filter((item) =>{
  //     var i = res.findIndex(x=>(x.recipientId == item.recipientId));
  //     if(i<=-1 && item.senderId == this.senderId || item.recipientId == this.senderId){
  //       res.push(item);
  //     }
  //     return null;
  //   });
    
  //   this.receiverNames = res;

  //   this.receiverNames.forEach((e)=>{
  //     if(e.recipientId == this.senderId){
  //       this.receiverNames.splice(this.receiverNames.indexOf(e),1);
  //     }
  //   })
    
    
    // console.log(this.receiverNames);
    // this.receiverNames.forEach((element,index)=>{
    //       if(element.senderId!=this.senderId) {this.receiverNames.splice(index,1);}

    // this.receiverNames.forEach((element,index)=>{
    //       if(element.senderId===this.senderId) {this.receiverNames.splice(index,1);}

    //    });
    })

  
    
  }
  


  senderId = localStorage.getItem('loginId');
  recipientId = this.chatService.recipientId;
  senderName = this.chatService.senderName;
  recipientName = this.chatService.recipientName;

  // senderId = "S123"
  // recipientId = "R124"
  // senderName = "ABCD";
  // recipientName = "IJKL";

  // senderId = "S123"
  // recipientId = "R125"
  // senderName = "ABCD";
  // recipientName = "MNOP";

  // senderId = "R124"
  // recipientId = "R125"
  // senderName = "IJKL";
  // recipientName = "MNOP";

  // senderId = "S123"
  // recipientId = ""
  // senderName = "";
  // recipientName = "";


  // senderId = "R123"
  // recipientId = "S123"
  // senderName = "EFGH";
  // recipientName = "ABCD";

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
    this.ngOnInit();
    // this.chatMethod(this.senderId,this.recipientId);
  }


  selectedReceiver(receiver:any){
    console.log(receiver);
    if(this.senderId == receiver.senderId){
    this.chatService.getMessages(this.senderId,receiver.recipientId).subscribe((data)=>{
      this.recipientId = receiver.recipientId;
      this.senderName = receiver.senderName;
      this.getChats = data;
      this.getChats.reverse();
      this.recipientName = receiver.recipientId.split('@',2)[0];
      this.receiverInitials = receiver.recipientId.charAt(0);
    })
  }
  else{
    this.chatService.getMessages(receiver.recipientId,receiver.senderId).subscribe((data)=>{
      this.getChats = data;
      console.log(data);
      console.log(receiver.recipientId,receiver.senderId);
      this.recipientId = receiver.senderId;
      this.senderName = receiver.recipientName;
      this.getChats.reverse();
      this.recipientName = receiver.senderId.split('@',2)[0];
      this.receiverInitials = receiver.senderId.charAt(0);
    })
  }
  
  }

  chatMethod(senderId:string,receiverId:string){
    this.sendChats.senderId = senderId;    
    this.sendChats.recipientId = receiverId;
    this.chatService.getMessages(senderId,receiverId).subscribe((data)=>{
      this.getChats = data;
      this.getChats.reverse();
      this.recipientName = receiverId.split('@',2)[0];
      this.receiverInitials = receiverId.charAt(0);
    })
  }

  
}
