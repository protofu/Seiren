import { NavLink } from "react-router-dom";
import styles from "./NavBar.module.css"

function NavBar() {
  return (
    <div className={styles.container}>
      <div className={styles.logo}>
        <div> Seiren </div>
      </div>
      <div className={styles.nav}>
        <NavLink to="/about">프로그램 소개</NavLink>
        <NavLink to="/voice-market">목소리 장터</NavLink>
        <NavLink to="/voice-study">목소리 등록</NavLink>
        <NavLink to="/my-page">마이페이지</NavLink>
      </div>
    </div>
  );
}

export default NavBar;