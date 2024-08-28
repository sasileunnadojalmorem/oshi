import zustand, { create } from "zustand";
import { UserItem } from "types/interface";

interface LoginUserStore{
    loginUser : UserItem | null;
    setLoginUser : (loginUser : UserItem) => void;
    resetloginuser : () => void;

};


const useLoginUserStore = create<LoginUserStore>(set =>({
    loginUser : null,
    setLoginUser : loginUser => set(state => ({...state, loginUser })),
    resetloginuser : () => set(state => ({...state, loginUser : null }))

}))

export default useLoginUserStore;