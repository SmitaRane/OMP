import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../person';
import { ApiResponse } from '../response.object';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private baseUrl = 'http://127.0.0.1:8080/persons';

  constructor(private http: HttpClient) { }

  // 1. Get all employees
  getAllEmployees(): Observable<ApiResponse<User[]>> {
    return this.http.get<ApiResponse<User[]>>(this.baseUrl);
  }

  // 2. Create new user
  createUser(user: User): Observable<ApiResponse<User>> {
    return this.http.post<ApiResponse<User>>(this.baseUrl, user);
  }

  // 3. Delete user by ID
  deleteUser(uuid: string): Observable<ApiResponse<null>> {
    return this.http.delete<ApiResponse<null>>(`${this.baseUrl}/${uuid}`);
  }


  // 4. Login user
  login(user: { primaryEmail: string; password: string }): Observable<ApiResponse<User>> {
    return this.http.post<ApiResponse<User>>('http://127.0.0.1:8080/persons/login', user);
  }

  updateUser(user: User): Observable<ApiResponse<User>> {
    // Assumes your API inspects the body.user.id to know which record to update
    return this.http.put<ApiResponse<User>>(this.baseUrl, user);
  }

}
