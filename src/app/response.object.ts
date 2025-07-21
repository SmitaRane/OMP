export interface ApiResponse<T> {
  success: boolean;
  status_code: number;
  message: string;
  url: string;
  timestamp: string;
  data: T;
}
