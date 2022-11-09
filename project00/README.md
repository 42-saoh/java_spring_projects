# Project00

## 보드 만들기

### config

- spring boot 사용 (gradle, 2.74 version, dependencies : Lombok, spring web, thymeleaf)
- DB : postgres db
- 추가 dependencies : JPA, QueryDSL
- Java 18

### 고민 및 궁금증 및 개선(?)사항

- queryDSL 을 사용할 필요가 있을까? 현재 JPA 만 사용해도 되지 않을까?
    - 아직은 queryDSL 을 사용할 필요가 없는 것 같다. 복잡한 쿼리문을 사용하지 않기때문에 JPA 만 사용해도 될 것 같다. 나중에 사용한다면 [https://tecoble.techcourse.co.kr/post/2021-08-08-basic-querydsl/#:~:text=QueryDSL의 장점은 다음,쿼리 작성이 편리하다](https://tecoble.techcourse.co.kr/post/2021-08-08-basic-querydsl/#:~:text=QueryDSL%EC%9D%98%20%EC%9E%A5%EC%A0%90%EC%9D%80%20%EB%8B%A4%EC%9D%8C,%EC%BF%BC%EB%A6%AC%20%EC%9E%91%EC%84%B1%EC%9D%B4%20%ED%8E%B8%EB%A6%AC%ED%95%98%EB%8B%A4). 의 방식으로 구현하면 될 것 같다.
    - 미리 구현하는 것도 나쁘지 않을수도? << 둘 다 사용할 수 있게 미리 틀은 구현하는 것이 좋을 것 같다.
- 파일을 업로드하고 저장하는 방식
    1. board 에 filename 이란 필드를 만든다
    2. 파일을 관리하는 entity 를 따로 만들어 1:1 관계로 board 에 연결을 한다.
    - 처음 board 에 filename 달려했지만 걸리는 점이 있다.
        1. 게시글 등록을 누르면 파일이 같이 등록이 되는 것이 아니라 파일을 업로드하고 게시글을 등록하는게 이전에 해봤던 방법이다. (하지만 파일 업로드와 같이 등록할 수 있다면? 그럼 해결되는 거 아닌가?)
        2. 업로드 폼에서 파일을 여러번 업로드 할 수 있다는 점
        3. 파일을 업로드하고 게시글을 등록하지 않을 수 있다는 점
    - 해결 : 스프링은 Multipart 를 제공한다. 이것은 다중으로 들어오는 데이터를 처리할 수 있다. 1번으로 하면 될 것 같다. 대신 fileUuid 필드를 만들어 같이 관리하는 것도 좋을 것 같다.
        - multipart 클레스에 대해 공부를 해봐야 할 것 같다.
- 파일 download 방법
    1. 현재 방법 
        
        ```jsx
        HttpServletResponse response;
        ServletOutputStream servletOutputStream = response.getOutputStream());
        while ((data = (fileInputStream.read(buf, 0, buf.length))) != -1) {
                        servletOutputStream.write(buf, 0, data);
        }
        ```
        
        - file 스트림에서 read 하여 response 스트림에 write 한다.
        - 스트림을 사용하여 이 메소드에서는 return value 는 없다.
    2. 다른 방법
        
        ```jsx
        public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws IOException {
                Resource resource = resourceLoader.getResource("classpath:" + filename);        
                File file = resource.getFile();
                Tika tika = new Tika();
                String mediaType = tika.detect(file);
        
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + resource.getFilename() +"\"")
                        .header(HttpHeaders.CONTENT_TYPE, mediaType)
                        .header(HttpHeaders.CONTENT_LENGTH, file.length()+"")
                        .body(resource);
            }
        ```
        
        - responseEntity 와 Resource 클래스를 사용하여 response의 바디에 파일데이터를 넣는다.
        - 어떤게 더 좋은 방법일까?
- 스프링 빈을 직접 등록하는 것이 좋다 해서 등록했는데 이것을 사용하는 이유는 동일한 인터페이스를 가진 여러 클래스를 갈아 끼우기 편하기 때문인데 지금 굳이 사용할 필요가 있을까? (사용하는게 맞나?)
- 모든 익셉션을 다 처리하는게 무조건 맞을까? (서버는 터지면 절대 안 되기 때문에) 익셉션이 발생했으면 response 도 다 처리해주는게 맞을 것 같다.
- 스프링 MVC 에서 view에 대한 고찰
    - view 를 서버에서 처리하는 이유가 있을까?
    - view 를 공부하는 이유는 프론트에 대한 이해정도일까?
    - controller와 model 을 구현하는 것보다 view 를 만드는 것에 시간을 많이 잡아 먹혔다. → view 에 많은 시간을 쓰는것이 맞을까?