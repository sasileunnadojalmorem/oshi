// src/stores/useLoginUserStore.ts
import { create } from "zustand";
import { UserItem } from "types/interface";

interface LoginUserStore {
    loginUser: UserItem | null;
    setLoginUser: (loginUser: UserItem) => void;
    resetLoginUser: () => void;
}

const useLoginUserStore = create<LoginUserStore>((set) => ({
    loginUser: null,
    setLoginUser: (loginUser) => set({ loginUser }),
    resetLoginUser: () => set({ loginUser: null }),
}));

export default useLoginUserStore;