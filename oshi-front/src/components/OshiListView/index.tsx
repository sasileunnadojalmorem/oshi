import React, { useRef, useState, useEffect } from 'react';
import './style.css';
import OshiListView from 'components/OshiListView/oshilistview';

// DTO 인터페이스 가져오기
import { OshiResponseDto,GetUserOshiResponseDto } from 'apis/response/oshi';


export default function View_Myoshi() {
  const containerRef = useRef<HTMLDivElement>(null);
  const isDragging = useRef(false);  // 드래그 상태를 추적
  const [oshiList, setOshiList] = useState<OshiResponseDto[]>([]); // 오시 리스트 상태 관리
  const [loading, setLoading] = useState<boolean>(true); // 로딩 상태 관리
  const [hasData, setHasData] = useState<boolean>(false); // 데이터 유무를 상태로 관리

  // API 호출 또는 모의 데이터를 가져오는 함수
  const fetchOshiList = async () => {
    try {
      // 실제 API 호출 예시 (모의 데이터를 사용)
      const response: GetUserOshiResponseDto = {
        oshiList: [
          {
            oshiId: 1,
            name: 'Oshi 1',
            description: 'This is Oshi 1',
            imageUrl: 'https://example.com/image1.jpg',
            status: 1,
          },
          {
            oshiId: 2,
            name: 'Oshi 2',
            description: 'This is Oshi 2',
            imageUrl: 'https://example.com/image2.jpg',
            status: 1,
          },
        ],
      };

      // 데이터를 상태에 저장하고 데이터 유무에 따라 상태 설정
      setOshiList(response.oshiList);
      setHasData(response.oshiList.length > 0);  // 데이터가 있으면 true
    } catch (error) {
      console.error('Failed to fetch Oshi list', error);
    } finally {
      setLoading(false); // 로딩 완료
    }
  };

  useEffect(() => {
    fetchOshiList(); // 컴포넌트 마운트 시 오시 리스트 데이터를 가져옴
  }, []);

  const handleMouseDown = () => {
    isDragging.current = true;  // 드래그 시작
  };

  const handleMouseMove = (event: React.MouseEvent) => {
    if (!isDragging.current) return;  // 드래그 중이 아닐 때 무시

    const container = containerRef.current;
    if (container) {
      container.scrollLeft -= event.movementX;  // 마우스 이동에 따라 스크롤
    }
  };

  const handleMouseUp = () => {
    isDragging.current = false;  // 마우스 버튼을 놓으면 드래그 종료
  };

  const handleMouseLeave = () => {
    isDragging.current = false;  // 마우스가 컨테이너를 벗어나면 드래그 종료
  };

  // 로딩 상태일 때 보여줄 내용
  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <div className="middle-box">
        <div
          className="oshi-container"
          ref={containerRef}
          onMouseDown={handleMouseDown}
          onMouseMove={handleMouseMove}
          onMouseUp={handleMouseUp}
          onMouseLeave={handleMouseLeave}
        >
          {hasData ? (
            oshiList.map((value, index) => (
              <OshiListView
                key={index}
                Oshiinfo={{
                  oshiId: value.oshiId,
                  oshiImage: value.imageUrl || '', // 이미지가 없을 경우 빈 값 처리
                  oshiName: value.name,
                }}
              />
            ))
          ) : (
            <div>데이터가 없습니다.</div>  // 데이터가 없을 때 보여줄 내용
          )}
        </div>
      </div>
    </div>
  );
}