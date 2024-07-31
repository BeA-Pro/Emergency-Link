export class LoginData {
  private email: string;
  private pwd: string;

  constructor(email: string, pwd: string) {
    this.email = email;
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