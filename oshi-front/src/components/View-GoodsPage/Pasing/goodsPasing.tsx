import React from 'react';
import { PagenatedGoods } from 'types/interface';
import PageItem from 'components/View-CategoryPage/CategoryListitem/pasing/page-item'
import './style.css';
interface Props {
  pageitem: PagenatedGoods;
}

export default function GoodsPasing({ pageitem }: Props) {
  const { Pageinfo } = pageitem;
  const { currentPage, totalPages } = Pageinfo;

  const handlePageClick = (pageNumber: number) => {
    console.log(`Page ${pageNumber} clicked`);
  };

  const isPrevButtonActive = currentPage > 10;
  const isNextButtonActive = Math.ceil(totalPages / 10) > Math.ceil(currentPage / 10);

  // 수정된 부분: pageindex와 pagecount 계산
  const pageindex = Math.floor((currentPage - 1) / 10) * 10;
  const pagecount = totalPages - pageindex > 10 ? 10 : totalPages - pageindex;

  return (
    <div>
      <div className='page-box'>
        {/* 이전 버튼 */}
        <div
          className={`page-button-prev ${isPrevButtonActive ? 'active' : 'disabled'}`}
          onClick={isPrevButtonActive ? () => handlePageClick(currentPage - 10) : undefined}
        >
          Prev
        </div>

        {/* 페이지 번호 */}
        {[...Array(pagecount)].map((_, index) => (
          <PageItem
            key={index}
            pageNumber={pageindex + index + 1}
            isActive={pageindex + index + 1 === currentPage}
            onClick={() => handlePageClick(pageindex + index + 1)}
          />
        ))}

        {/* 다음 버튼 */}
        <div
          className={`page-button-next ${isNextButtonActive ? 'active' : 'disabled'}`}
          onClick={isNextButtonActive ? () => handlePageClick(currentPage + 10) : undefined}
        >
          Next
        </div>
      </div>
    </div>
  );
}
