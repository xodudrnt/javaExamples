# 자바 examples

## 1. executor 예제

excutorExampleService

### 개요

 - Thread Pool 사용
 - 무거운 Thread 선 할당 가능
 - Task 와 Thread 생성 및 관리 분리
 - 쓰레드 풀안의 쓰레드는 한번해 하나씩 여러 태스크를 실행
 - Executor Service를 더이상 필요 없으면 중지
 - Executor Service가 멈추면 모든 쓰레드도 중지
 - FixedPool 사용시 쓰레드 숫자만큼의 태스크가 먼저 실행된다.
 - CachedThreadPool 사용시 태스크 숫자에 따라 쓰레드 숫자가 가변된다 
 
## 2. Syncronized 예제

synchronizedExampleService

### 개요

 - 다중 쓰레드가 한개의 자원을 사용하려 할때 한개의 Thread만 접근 가능하도록 함.
 - 남발할 경우 성능상의 Issue 존재

## 3. 생성자 소비자 패턴  예제

producerConsumerExampleService

### 개요

 - 구성 : 생성자, 전달 매개체, 소비자
 - 동작 : 생성자가 Task를 계속 생상하여 전달 매개체에 쌓고 소비자가 Task를 실행
 - 전달 매게체는 주로 BlockingQueue를 사용

## 4. Join 예제

joinExampleService

### 개요

 - 쓰레드가 멈출때까지 대기

## 5. Fork Join Action / Task

forkJoinExampleService

### 개요

 - 자바 7 이상 지원
 - 큰 업무를 나누어 배분한 후 일을 취합
 - 분할 정복과 유사
 - Recursive Action과 Recursive Task가 있다

## 6. Future + Excutor 예제

futureExampleService

### 개요

 - Callable의 작업이 완료될 때까지 Blocking 되었다가 Future 객체를 리턴한다. 
 - Future 객체는 최종 결과를 얻는데 사용

## 7. CountLatch 예제

CountLatchExampleService

### 개요

 - 멀티 쓰레드에서 모든 동작이 끝나기를 기다려야 하는 상황에 사용
 - 1 이상의 카운트를 인자값으로 받음
 - 특정 메솓드 내부에서 awit() 메소드로 대기상태 생성
 - Count 가 0이되면 대기상태 해제

## 8. NIO Chat Server 예제

serverExampleService

### 개요

#### JAVA NIO 

NIO = New + I/O

 - 기존에서 사용하는 IO 에서 스트림 기반의 입출력을 사용하여 느린 문제를 Channel과 buffer를 활용하여 성능 향상
 - Buffer 는 시스템의 것을 사용함 (속도 향상)
 - watcher 도 보면 좋음
