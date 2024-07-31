export class LoginData {
  private userId: string;
  private pwd: string;

  constructor(userId: string, pwd: string) {
    this.userId = userId;
    this.pwd = pwd;
  }
}

export class JoinData{
  private email: string;
  private pwd: string;
  private category: number;
  constructor( email: string,pwd : string,category: number) {
    this.email = email;
    this.pwd = pwd;
    this.category = category;
  }
}