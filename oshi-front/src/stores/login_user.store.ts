import zustand, { create } from "zustand";
import { UserItem } from "types/interface";

interface LoginUserStore{
    loginUser : UserItem | null;
    setUser : (loginUser : UserItem) => void;
    restloginuser : () => void;

};


const useLoginUserStore = create<LoginUserStore>(set =>)