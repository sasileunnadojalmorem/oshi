import React from 'react'
import './style.css'
import OshiListItem from 'components/View-CategoryPage'
import { oshimock,categorymock,goodsmock, } from 'mocks'
import GoodsItemListPage from 'components/View-GoodsPage'
export default function Oshilistcategory() {
  return (
    <div>
      <OshiListItem
        oshiitem={oshimock[0]}
        categoryitem={categorymock[0]}
        goodsitem={goodsmock[0]}
      />
      <GoodsItemListPage />
    </div>
  )
}
