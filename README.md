## 결제

### 헥사고날 아키텍쳐(Hexagonal Architecture)

### 헥사고날 아키텍쳐 구조에 대해서
    1. 패키지 구조  
    2. 헥사고날 통신 구조

#### 구현 내용
    * 맴버 추가
    * 맴버 조회

## VAULT

### 정책(Policy) ACL
```shell
path "kv-v2/data/encryted/data/dbkey"
{
   capabilities = ["create", "read", "update", "delete", "sudo", "list"]
}
```
### 토큰 발급
```shell
vault write auth/token/create policies=default
```
### 명령어
```bash
curl --header "X-Vault-Token: hvs.CAESILswsNlLPIB6Ohl3JV75saTj45yZwwkc9ED6IOmsIeTXGh4KHGh2cy5LTnhHQUdFclRvT0w2bElsRlBNN0NVSjg" http://127.0.0.1:8200/v1/kv-v2/data/encryted/data/dbkey
```

