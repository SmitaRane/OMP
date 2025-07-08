export interface User {
  id?: number;
  uuid?: string;
  firstname: string;
  lastname: string;
  primaryEmail: string;
  secondaryEmail: string;
  contact: string;
  education: string;
  password: string;
  created_at?: string;
  status?: string;
}