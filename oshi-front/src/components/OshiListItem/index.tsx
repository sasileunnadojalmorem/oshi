import React from 'react' ;
import './style.css';

// componet: Oshi List Item 컴포넌트


export default function OshiListItem() {


// render: Oshi List Item 컴포넌트 렌더링
  return (
    <div className='oshi-list-item'>
        <div className='oshi-list-main'>
            <div className='oshi-list-item-top'> 
                <div className='oshi-list-item-image' style={{backgroundImage: 'url()'}}></div>
                <div className='oshi-list-item-who'>
                    <div className='oshi-list-item-oshi'>{'아이유'}</div>
                    <div className='ohsi-list-item-category'>{'공식: 2024 시즌 그리팅'}</div>
                    <div className='oshi-list-item-goods'>{'포토 카드'}</div>
                </div>
            </div>
            <div className='oshi-list-item-middle'> 
                <div className='ohsi-list-item-search-category'>{'카테고리별'}</div>
                <div className='oshi-list-item-search-goods'>{'굿즈별'}</div>
            </div>
            <div className='oshi-list-item-bottom'> 
                <div className='oshi-list-item-goods-type'>
                    <div className='oshi-list-item-goods-type-text'>{'굿즈 종류'}</div>
                    <div className='oshi-list-item-goods-type-select'>{'모두 보기'}</div>
                </div>
                <div className='oshi-list-item-goods-sort'>{'최신순 | 발매순 | 인기순'}</div>
            </div>
        </div>
    </div>
  )
}
    