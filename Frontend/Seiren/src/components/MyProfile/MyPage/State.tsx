import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"; // useNavigate 추가
import { customAxios } from "../../../libs/axios";
import styles from "./State.module.css";

function State({ voiceDetail, checkState, setCheckState }) {
  const [buttonText, setButtonText] = useState("");
  const navigate = useNavigate(); // useNavigate 훅 사용
  
  useEffect(() => {
    switch (voiceDetail.state) {
      case 0:
      case 1:
        setButtonText("녹음하러가기");
        break;
      case 2:
        setButtonText("판매 등록 하러가기");
        break;
      case 3:
        setButtonText("판매 중지");
        break;
      case 4:
        setButtonText("재판매");
        break;
      default:
        setButtonText("");
        break;
    }
  }, [voiceDetail]);


  const handleButtonClick = () => {
    if (voiceDetail.productId) {
      customAxios
        .put(`product/state/${voiceDetail.productId}`)
        .then(response => {
          console.log(response);
          if(checkState === false){
            setCheckState(true);
          }else{
            setCheckState(false);
          }
        })
        .catch(error => {
          console.error("API 호출 중 오류 발생:", error);
        });
    }

    // 버튼 클릭 시 해당 경로로 이동
    switch (voiceDetail.state) {
      case 0:
      case 1:
        navigate("/voice-record");
        break;
      case 2:
        navigate(`/product-custom/${voiceDetail.voiceId}`);
        break;
      default:
        break;
    }
  };

  return (
    <div className={styles.box}>
      {/* 텍스트 렌더링 */}
      {(voiceDetail.state === 0 || voiceDetail.state === 1) && (
        <p className={styles.text}>녹음을 완료해 주세요</p>
      )}
      {voiceDetail.state === 2 && (
        <p className={styles.text}>판매 등록 해주세요!</p>
      )}
      {voiceDetail.state === 3 && (
        <p className={styles.text}>판매 중</p>
      )}
      {voiceDetail.state === 4 && (
        <p className={styles.text}>판매 중지</p>
      )}

      {/* 버튼 렌더링 */}
      {buttonText && (
        <button onClick={handleButtonClick} className={styles.btn}>
          {buttonText}
        </button>
      )}
    </div>
  );
}

export default State;
