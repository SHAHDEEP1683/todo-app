import "./App.css";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";

import Welcome from "./pages/welcome.jsx";
import Login from "./pages/Login.jsx";
import AdminRegistration from "./pages/AdminRegistration.jsx";
import MemberRegistration from "./pages/MemberRegistration.jsx";
import Admin from "./pages/Admin.jsx";
import SuperAdmin from "./pages/SuperAdmin.jsx";
import Member from "./pages/Member.jsx";

const getUserRole = () => localStorage.getItem("role");

function ProtectedRoute({ children, allowedRoles }) {
  const role = getUserRole();
  if (!role || !allowedRoles.includes(role)) {
    return <Navigate to="/login" />;
  }
  return children;
}

function App() {
  
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Welcome />} />
        <Route path="/login" element={<Login />} />
        <Route path="/adminregister" element={<AdminRegistration />} />
        <Route path="memberregister" element={<MemberRegistration />} />
        <Route path="/admin" element={
          <ProtectedRoute allowedRoles={["ADMIN"]}>
              <Admin />
          </ProtectedRoute>
          }
        />
         <Route path="/admin" element={
          <ProtectedRoute allowedRoles={["ADMIN"]}>
              <Admin />
          </ProtectedRoute>
          }
        />
        <Route
          path="/superadmin"
          element={
            <ProtectedRoute allowedRoles={["SUPER_ADMIN"]}>
              <SuperAdmin />
            </ProtectedRoute>
          }
        />
        <Route
          path="/member"
          element={
            <ProtectedRoute allowedRoles={["MEMBER"]}>
              <Member />
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
