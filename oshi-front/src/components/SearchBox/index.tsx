import React from 'react'
import './style.css'
import Dropdown from 'components/View-CategoryPage/dropdown'
import { Goodstypeitem } from 'types/interface'
export default function SearchTypeDropDown() {
    const goodstype: Goodstypeitem = {
        goodstype: ['최애 검색', '카테고리 검색', '굿즈 검색'],

    }
  // 
    return (
    <div className='search-type-box'>
        <Dropdown Types={goodstype}/>
    </div>
  )
}
