// -*-C++-*-
/*!
 * @file  TestIDLSVC_impl.h
 * @brief Service implementation header of TestIDL.idl
 *
 */

#include "TestIDLSkel.h"

#ifndef TESTIDLSVC_IMPL_H
#define TESTIDLSVC_IMPL_H
 
/*!
 * @class ComFkSVC_impl
 * Example class implementing IDL interface ComFk
 */
class ComFkSVC_impl
 : public virtual POA_ComFk,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~ComFkSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   ComFkSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~ComFkSVC_impl();

   // attributes and operations
   ValueList* set_value(const ValueList& frm, ValueList_out frmo, ValueList& frmio);
   char* set_string(const char* frm, StringList_out frmo, char*& frmio);
   LongArray_slice* set_longArray(const LongArray frm, LongArray frmo, LongArray frmio);
   ValueListArray_slice* set_longArrayv(const ValueListArray frm, ValueListArray_out frmo, ValueListArray frmio);
   color set_color(color frm, color& frmo, color& frmio);
   Frame0 set_tool0(const Frame0& frm, Frame0_out frmo, Frame0& frmio);
   Frame1* set_tool1(const Frame1& frm, Frame1_out frmo, Frame1& frmio);
   Frame2* set_tool2(const Frame2& frm, Frame2_out frmo, Frame2& frmio);
   Frame3 set_tool3(const Frame3& frm, Frame3_out frmo, Frame3& frmio);
   Frame4* set_tool4(const Frame4& frm, Frame4_out frmo, Frame4& frmio);
   Frame5* set_tool5(const Frame5& frm, Frame5_out frmo, Frame5& frmio);
   Frame1List* set_toolSeq1(const Frame1List& frm, Frame1List_out frmo, Frame1List& frmio);
   Frame2List* set_toolSeq2(const Frame2List& frm, Frame2List_out frmo, Frame2List& frmio);
   Frame1Array_slice* set_toolArray1(const Frame1Array frm, Frame1Array_out frmo, Frame1Array frmio);
   Frame2Array_slice* set_toolArray2(const Frame2Array frm, Frame2Array_out frmo, Frame2Array frmio);

};



#endif // TESTIDLSVC_IMPL_H


