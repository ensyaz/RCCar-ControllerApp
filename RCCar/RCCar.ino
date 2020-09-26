char komut;
int in1=53;
int in2=52;
int in3=51;
int in4=50;
int ENA=49;
int ENB=48;
int HIZ=140;

void _Ileri() {
 analogWrite(ENA,HIZ); analogWrite(ENB,HIZ); digitalWrite(in1,HIGH);
 digitalWrite(in2,LOW); digitalWrite(in3,HIGH); digitalWrite(in4,LOW);
 Serial.println("İleri Git!");
}

void _Geri() {
 analogWrite(ENA,HIZ); analogWrite(ENB,HIZ); digitalWrite(in1,LOW);
  digitalWrite(in2,HIGH); digitalWrite(in3,LOW); digitalWrite(in4,HIGH);
 Serial.println("Geri Git!");
}

void _Sol() {
 analogWrite(ENA,HIZ); analogWrite(ENB,HIZ); digitalWrite(in1,HIGH);
 digitalWrite(in2,LOW); digitalWrite(in3,LOW); digitalWrite(in4,HIGH);
 Serial.println("Sola Git!");
}

void _Sag() {
 analogWrite(ENA,HIZ); analogWrite(ENB,HIZ); digitalWrite(in1,LOW);
 digitalWrite(in2,HIGH); digitalWrite(in3,HIGH); digitalWrite(in4,LOW);
 Serial.println("Sağa Git!");
}

void _Dur() {
 digitalWrite(ENA,LOW); digitalWrite(ENB,LOW); Serial.println("Dur!");
}

void setup()
{
 Serial.begin(9600);
 pinMode(in1,OUTPUT);
 pinMode(in2,OUTPUT);
 pinMode(in3,OUTPUT);
 pinMode(in4,OUTPUT);
 pinMode(ENA,OUTPUT);
 pinMode(ENB,OUTPUT);
 _Dur();
}

void loop()
 {
 komut=Serial.read();
 /// Yönlere göre hareketler ///

 if(komut=='f'|| komut=='F') { //ileri
 _Ileri();
 }
 else if(komut=='b'|| komut=='B') { //geri
 _Geri();
 }
 else if(komut=='l'|| komut=='L') { // sol
 _Sol();
 }
 else if(komut=='r'|| komut=='R') { //sağ
 _Sag();
 }
 else if(komut=='s'|| komut=='S') { //dur
 _Dur();
 }


 /// Hız Ayarları ////
 else if(komut=='1') HIZ=100;
 else if(komut=='2') HIZ=120;
 else if(komut=='3') HIZ=140;
 else if(komut=='4') HIZ=160;
 else if(komut=='5') HIZ=180;
 else if(komut=='6') HIZ=200;
 else if(komut=='7') HIZ=220;
 else if(komut=='8') HIZ=230;
 else if(komut=='9') HIZ=240;
 else if(komut=='q') HIZ=250;
 }
