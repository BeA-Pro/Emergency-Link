export class LoginData {
  private userId: string;
  private pwd: string;

  constructor(userId: string, pwd: string) {
    this.userId = userId;
    this.pwd = pwd;
  }
}

export class UserInfoDto{
  private userId: string;
  private userPwd: string;
  private userName: string;

  constructor( userId: string,pwd : string, name: string) {
    this.userId = userId;
    this.userPwd = pwd;
    this.userName = name;
  }
}

export interface JwtPayload {
  userId: string;
  category: number;
  exp: number; // 만료 시간 등 추가적인 정보가 있을 수 있습니다.
}