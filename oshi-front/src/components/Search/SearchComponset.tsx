// src/components/Search/SearchComponent.tsx
import React, { useState } from 'react';
import { SearchCategoryRequestDto } from 'apis/request/category';
import { SearchGoodsRequestDto } from 'apis/request/goods';
import { SearchOshiRequestDto } from 'apis/request/oshi';
import { SearchCategoryResponseDto } from 'apis/response/category';
import { SearchGoodsResponseDto } from 'apis/response/goods';
import { SearchOshiResponseDto } from 'apis/response/oshi';
import { searchOshi } from 'apis/controller/Oshi';
import { searchCategoryRequest } from 'apis/controller/Category';
import { searchGoodsRequest } from 'apis/controller/Goods';

const SearchComponent = () => {
  const [searchType, setSearchType] = useState<'oshi' | 'category' | 'goods'>('oshi');
  const [keyword, setKeyword] = useState<string>('');
  const [results, setResults] = useState<any[]>([]);

  // 검색 타입 변경 핸들러
  const handleTypeChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSearchType(e.target.value as 'oshi' | 'category' | 'goods');
  };

  // 검색어 변경 핸들러
  const handleKeywordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setKeyword(e.target.value);
  };

  // 타입 가드 함수: 응답이 SearchOshiResponseDto 타입인지 확인
  const isSearchOshiResponse = (response: any): response is SearchOshiResponseDto => {
    return 'oshiEntityList' in response;
  };

  // 타입 가드 함수: 응답이 SearchCategoryResponseDto 타입인지 확인
  const isSearchCategoryResponse = (response: any): response is SearchCategoryResponseDto => {
    return 'categoryEntities' in response;
  };

  // 타입 가드 함수: 응답이 SearchGoodsResponseDto 타입인지 확인
  const isSearchGoodsResponse = (response: any): response is SearchGoodsResponseDto => {
    return 'baseGoodsEntity' in response;
  };

  // 검색 실행 핸들러
  const handleSearch = async () => {
    if (!keyword.trim()) return alert('검색어를 입력하세요.');

    try {
      let response;

      if (searchType === 'oshi') {
        const requestBody: SearchOshiRequestDto = { keyword };
        response = await searchOshi(requestBody);

        if (isSearchOshiResponse(response)) {
          setResults(response.oshiEntityList); // Oshi API 결과 처리
        } else {
          alert('오시 검색에 실패했습니다.');
        }

      } else if (searchType === 'category') {
        const requestBody: SearchCategoryRequestDto = { keyword };
        response = await searchCategoryRequest(requestBody);

        if (isSearchCategoryResponse(response)) {
          setResults(response.categoryEntities); // Category API 결과 처리
        } else {
          alert('카테고리 검색에 실패했습니다.');
        }

      } else if (searchType === 'goods') {
        const requestBody: SearchGoodsRequestDto = { keyword };
        response = await searchGoodsRequest(requestBody);

        if (isSearchGoodsResponse(response)) {
          setResults(response.baseGoodsEntity); // Goods API 결과 처리
        } else {
          alert('굿즈 검색에 실패했습니다.');
        }
      }
    } catch (error) {
      console.error('검색 실패:', error);
      alert('검색 중 오류가 발생했습니다.');
    }
  };

  // 검색 결과 출력
  const renderResults = () => {
    if (searchType === 'oshi') {
      return results.map((result: SearchOshiResponseDto['oshiEntityList'][0]) => (
        <div key={result.oshiId}>
          <img src={result.imageUrl} alt={result.name} />
          <h4>{result.name}</h4>
          <p>{result.description}</p>
        </div>
      ));
    } else if (searchType === 'category') {
      return results.map((result: SearchCategoryResponseDto['categoryEntities'][0]) => (
        <div key={result.categoryId}>
          <h4>{result.categoryName}</h4>
          <p>{result.description}</p>
        </div>
      ));
    } else if (searchType === 'goods') {
      return results.map((result: SearchGoodsResponseDto['baseGoodsEntity'][0]) => (
        <div key={result.goodsId}>
          <h4>{result.name}</h4>
          <p>{result.description}</p>
        </div>
      ));
    }
  };

  return (
    <div>
      <h2>검색</h2>
      <div>
        {/* 드롭다운 - 검색 타입 선택 */}
        <select value={searchType} onChange={handleTypeChange}>
          <option value="oshi">오시</option>
          <option value="category">카테고리</option>
          <option value="goods">굿즈</option>
        </select>

        {/* 검색어 입력 */}
        <input
          type="text"
          placeholder="검색어를 입력하세요"
          value={keyword}
          onChange={handleKeywordChange}
        />

        {/* 검색 버튼 */}
        <button onClick={handleSearch}>검색</button>
      </div>

      {/* 검색 결과 출력 */}
      <div>{renderResults()}</div>
    </div>
  );
};

export default SearchComponent;