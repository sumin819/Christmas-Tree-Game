### 각 클래스의 역할
1)	Main.java

 내부 클래스 GamePanel과 GameThread를 사용해 전체적인 화면을 구성합니다. GamePanel은 게임 화면을 그래픽을 사용해 구성하고 객체을 생성하고 획득하는 것, 삭제하는 것 등의 모든 제어를 담당합니다. GameThread는 게임 전체의 스레드를 관리합니다. 

2)	Ornament.java
 
 원 오너먼트를 표현하는 클래스입니다. 생성되는 x좌표와 떨어지는 속도를 랜덤으로 설정합니다. falling()이란 함수를 두어 y좌표에 속도 값을 더해 아래로 떨어지는 모습을 구현하게 하였습니다. 

3)	Star.java
 
 별 오너먼트를 표현하는 클래스입니다. 생성되는 x좌표와 떨어지는 속도를 랜덤으로 설정합니다. falling()이란 함수를 두어 y좌표에 속도 값을 더해 아래로 떨어지는 모습을 구현하게 하였습니다.

4)	Bomb.java
 
 폭탄을 표현하는 클래스입니다. 생성되는 x좌표와 떨어지는 속도를 랜덤으로 설정합니다. falling()이란 함수를 두어 y좌표에 속도 값을 더해 아래로 떨어지는 모습을 구현하게 하였습니다.

5)	Audio.java
 
 오디오를 제어하는 클래스입니다. Thread를 상속받았습니다. 노래를 재생하는 playAudio함수가 있으며 run()에서 playAudio()를 호출합니다. Main.java에서 start()를 불러 노래를 재생합니다. 

6)	Starter.java

 Main 프로그램을 실행합니다. 
