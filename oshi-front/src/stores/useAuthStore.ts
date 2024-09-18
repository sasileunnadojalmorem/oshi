// src/stores/useAuthStore.ts
import { create } from "zustand";
import { UserItem } from "types/interface";

interface AuthStore {
  user: UserItem | null;
  accessToken: string | null;
  setUser: (user: UserItem) => void;
  setAccessToken: (token: string, expirationTime: number) => void;
  resetAuth: () => void;
}

const useAuthStore = create<AuthStore>((set) => ({
  user: null,
  accessToken: null,
  setUser: (user) => set({ user }),
  setAccessToken: (token, expirationTime) => {
    const expires = new Date(Date.now() + expirationTime * 1000);
    document.cookie = `accessToken=${token}; expires=${expires.toUTCString()}; path=/`;
    set({ accessToken: token });
  },
  resetAuth: () => set({ user: null, accessToken: null }),
}));

export default useAuthStore;