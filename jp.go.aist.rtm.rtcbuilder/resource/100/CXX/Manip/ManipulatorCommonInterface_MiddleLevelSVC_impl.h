// -*-C++-*-
/*!
 * @file  ManipulatorCommonInterface_MiddleLevelSVC_impl.h
 * @brief Service implementation header of ManipulatorCommonInterface_MiddleLevel.idl
 *
 */

#include "ManipulatorCommonInterface_DataTypesSkel.h"
#include "BasicDataTypeSkel.h"

#include "ManipulatorCommonInterface_MiddleLevelSkel.h"

#ifndef MANIPULATORCOMMONINTERFACE_MIDDLELEVELSVC_IMPL_H
#define MANIPULATORCOMMONINTERFACE_MIDDLELEVELSVC_IMPL_H
 
/*!
 * @class ManipulatorCommonInterface_MiddleSVC_impl
 * Example class implementing IDL interface ManipulatorCommonInterface_Middle
 */
class ManipulatorCommonInterface_MiddleSVC_impl
 : public virtual POA_ManipulatorCommonInterface_Middle,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~ManipulatorCommonInterface_MiddleSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   ManipulatorCommonInterface_MiddleSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~ManipulatorCommonInterface_MiddleSVC_impl();

   // attributes and operations
   RTC::RETURN_ID* closeGripper();
   RTC::RETURN_ID* getBaseOffset(RTC::HgMatrix offset);
   RTC::RETURN_ID* getFeedbackPosCartesian(RTC::CarPosWithElbow& pos);
   RTC::RETURN_ID* getMaxSpeedCartesian(RTC::CartesianSpeed& speed);
   RTC::RETURN_ID* getMaxSpeedJoint(RTC::DoubleSeq_out speed);
   RTC::RETURN_ID* getMinAccelTimeCartesian(::CORBA::Double& aclTime);
   RTC::RETURN_ID* getMinAccelTimeJoint(::CORBA::Double& aclTime);
   RTC::RETURN_ID* getSoftLimitCartesian(RTC::LimitValue& xLimit, RTC::LimitValue& yLimit, RTC::LimitValue& zLimit);
   RTC::RETURN_ID* moveGripper(RTC::ULONG angleRatio);
   RTC::RETURN_ID* moveLinearCartesianAbs(const RTC::CarPosWithElbow& carPoint);
   RTC::RETURN_ID* moveLinearCartesianRel(const RTC::CarPosWithElbow& carPoint);
   RTC::RETURN_ID* movePTPCartesianAbs(const RTC::CarPosWithElbow& carPoint);
   RTC::RETURN_ID* movePTPCartesianRel(const RTC::CarPosWithElbow& carPoint);
   RTC::RETURN_ID* movePTPJointAbs(const RTC::JointPos& jointPoints);
   RTC::RETURN_ID* movePTPJointRel(const RTC::JointPos& jointPoints);
   RTC::RETURN_ID* openGripper();
   RTC::RETURN_ID* pause();
   RTC::RETURN_ID* resume();
   RTC::RETURN_ID* stop();
   RTC::RETURN_ID* setAccelTimeCartesian(::CORBA::Double aclTime);
   RTC::RETURN_ID* setAccelTimeJoint(::CORBA::Double aclTime);
   RTC::RETURN_ID* setBaseOffset(const RTC::HgMatrix offset);
   RTC::RETURN_ID* setControlPointOffset(const RTC::HgMatrix offset);
   RTC::RETURN_ID* setMaxSpeedCartesian(const RTC::CartesianSpeed& speed);
   RTC::RETURN_ID* setMaxSpeedJoint(const RTC::DoubleSeq& speed);
   RTC::RETURN_ID* setMinAccelTimeCartesian(::CORBA::Double aclTime);
   RTC::RETURN_ID* setMinAccelTimeJoint(::CORBA::Double aclTime);
   RTC::RETURN_ID* setSoftLimitCartesian(const RTC::LimitValue& xLimit, const RTC::LimitValue& yLimit, const RTC::LimitValue& zLimit);
   RTC::RETURN_ID* setSpeedCartesian(RTC::ULONG spdRatio);
   RTC::RETURN_ID* setSpeedJoint(RTC::ULONG spdRatio);

};



#endif // MANIPULATORCOMMONINTERFACE_MIDDLELEVELSVC_IMPL_H


