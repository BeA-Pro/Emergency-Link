export class LoginData {
  private userId: string;
  private pwd: string;

  constructor(userId: string, pwd: string) {
    this.userId = userId;
    this.pwd = pwd;
  }
}

export class JoinData{
  private userId: string;
  private pwd: string;
  private category: number;
  constructor( userId: string,pwd : string,category: number) {
    this.userId = userId;
    this.pwd = pwd;
    this.category = category;
  }
}

export interface JwtPayload {
  userId: string;
  category: number;
  exp: number; // 만료 시간 등 추가적인 정보가 있을 수 있습니다.
}